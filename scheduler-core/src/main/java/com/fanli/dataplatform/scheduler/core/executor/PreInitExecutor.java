package com.fanli.dataplatform.scheduler.core.executor;

import com.fanli.dataplatform.scheduler.client.client.Const;
import com.fanli.dataplatform.scheduler.client.domain.InstanceDO;
import com.fanli.dataplatform.scheduler.client.domain.InstanceRelaDO;
import com.fanli.dataplatform.scheduler.client.domain.TaskDO;
import com.fanli.dataplatform.scheduler.client.domain.TaskRelaDO;
import com.fanli.dataplatform.scheduler.core.common.CoreConst;
import com.fanli.dataplatform.scheduler.core.dao.InstanceDAO;
import com.fanli.dataplatform.scheduler.core.dao.TaskDAO;
import com.fanli.dataplatform.scheduler.core.dao.proxy.InstanceDAOProxy;
import com.fanli.dataplatform.scheduler.core.utils.DateFormatUtils;
import com.fanli.dataplatform.scheduler.core.utils.DateUtils;
import com.fanli.dataplatform.scheduler.core.utils.TaskUtils;
import com.fanli.dataplatform.scheduler.core.utils.Utilities;
import com.fanli.dataplatform.scheduler.core.validator.TaskValidator;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.quartz.CronExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.File;
import java.text.ParseException;
import java.util.*;

/**
 * Created by adima on 14-3-23.
 */

public class PreInitExecutor {
    @Resource
    private TaskDAO taskDAO;

    @Resource
    private InstanceDAO instDAO;

    @Resource(name = "instDAOProxy")
    private InstanceDAOProxy instDAOProxy;

//    public InstanceDAOProxy getInstDAOProxy() {
//        return instDAOProxy;
//    }
//
//    public void setInstDAOProxy(InstanceDAOProxy instDAOProxy) {
//        this.instDAOProxy = instDAOProxy;
//    }

    private Logger logger = LoggerFactory.getLogger(PreInitExecutor.class);


    public void execute(Date begin,Date end) {
        logger.info("the initExecuter thread starts");
        //查询所有有效任务
        List<TaskDO> list = taskDAO.getValidateTaskList(CoreConst.TASK_VALIDATE);
        List<TaskRelaDO> relaList = taskDAO.getTaskRelaList();
        Map<Integer, TaskDO> taskMap = this.convertTaskMap(list);
        Map<Integer, List<TaskRelaDO>> relaMap = this.convertRelaMap(relaList);
        Map<Integer, List<TaskRelaDO>> reverseRelaMap = this.convertReverseRelaMap(relaList);

        //查询所有依赖

        //循环所有任务任务
        for (TaskDO task : list) {
            Date triggerTime = begin;
            CronExpression expression = null;
            try {
                expression = new CronExpression(task.getFreq());
            } catch (ParseException e) {
                logger.error(task.getTaskId() + "(" + task.getTaskName() + ")" + " " + task.getFreq() + " is illegal cron expression");
                continue;
            }
            String rtn[] = TaskValidator.validateCycle(task.getCycle());
            if (rtn[0].equals("0")) {
                logger.error(task.getTaskId() + "(" + task.getTaskName() + ") cycle " + task.getCycle() + " is illegal");
            } else {
                while (true) {
                    try {
                        triggerTime = expression.getNextValidTimeAfter(triggerTime);
                        if (triggerTime.getTime() > end.getTime()) {
                            break;
                        }
                        InstanceDO inst = this.generateInstance(task, relaMap.get(task.getTaskId()), triggerTime);
                        if (this.instDAO.getInstanceInfo(inst.getInstanceId()) != null) {
                            continue;
                        }
                        if (StringUtils.isBlank(null)) {
                            DynamicPriority dp = new DynamicPriority(inst.getTaskId(), inst.getPriority(), taskMap, reverseRelaMap);
                            //调用内部类获取任务score
                            Integer score = dp.calculateScore(inst.getTaskId(), inst.getPriority());
                            inst.setRunningPrio(score);
                            //将任务和依赖存储到intance表
                            this.instDAOProxy.saveInstance(inst);
                            logger.info(new StringBuilder().append(inst.getInstanceId()).append("(").
                                    append(inst.getTaskName()).append(") init successful;").append("score :=")
                                    .append(inst.getRunningPrio()).toString());
                        }
                    } catch (Exception e) {
                        logger.error(task.getTaskId() + "(" + task.getTaskName() + ") init error", e);
                    }
                }
            }

        }
        logger.info("the initExecuter thread ends");
    }


    private Map<Integer, List<TaskRelaDO>> convertRelaMap(List<TaskRelaDO> relaList) {
        Map<Integer, List<TaskRelaDO>> result = new HashMap<Integer, List<TaskRelaDO>>();
        for (TaskRelaDO rela : relaList) {
            if (!result.containsKey(rela.getTaskId())) {
                List<TaskRelaDO> tmp = new ArrayList<TaskRelaDO>();
                tmp.add(rela);
                result.put(rela.getTaskId(), tmp);
            } else {
                List tmp = result.get(rela.getTaskId());
                tmp.add(rela);
            }
        }
        return result;
    }

    private Map<Integer, List<TaskRelaDO>> convertReverseRelaMap(List<TaskRelaDO> relaList) {
        Map<Integer, List<TaskRelaDO>> result = new HashMap<Integer, List<TaskRelaDO>>();
        for (TaskRelaDO rela : relaList) {
            if (!result.containsKey(rela.getPreId())) {
                List<TaskRelaDO> tmp = new ArrayList<TaskRelaDO>();
                tmp.add(rela);
                result.put(rela.getPreId(), tmp);
            } else {
                List tmp = result.get(rela.getPreId());
                tmp.add(rela);
            }
        }
        return result;
    }

    private Map<Integer, TaskDO> convertTaskMap(List<TaskDO> list) {
        Map<Integer, TaskDO> result = new HashMap<Integer, TaskDO>();
        for (TaskDO task : list) {
            result.put(task.getTaskId(), task);
        }
        return result;
    }

    private InstanceDO generateInstance(TaskDO task, List<TaskRelaDO> relaList, Date triggerTime) {
        String instanceId = null, cycle = null, command = null,
                para2 = null, para3 = null, logPath = null, calDt = null,
                desc = null;
        Integer status = null;

        String[] rtn = TaskValidator.validateTask(task);
        instanceId = TaskUtils.generateInstanceID(task.getTaskId(), task.getCycle(), triggerTime);

        if (rtn[0].equals("1")) {
            status = Const.JOB_STATUS.JOB_INIT.getValue();
            desc = Const.JOB_STATUS.JOB_INIT.getDesc();
        } else {
            status = Const.JOB_STATUS.JOB_INTERNAL_ERROR.getValue();
            desc = Const.JOB_STATUS.JOB_INTERNAL_ERROR.getDesc();
            logger.error(task.getTaskId() + "(" + task.getTaskName() + ")" + " init error;message:" + rtn[1]);
        }

        String currTime = DateFormatUtils.getFormatter().format(new Date());

        //todo
        command = StringUtils.isBlank(task.getCommand()) ? task.getCommand() : Utilities.ParameterUtils.resourceParamHandle(DateUtils.getReplaceCal(task.getCommand(), "offset", task.getOffset(), triggerTime))
                .replace("${task_id}", String.valueOf(task.getTaskId()))
                .replace("${instance_id}", instanceId)
                .replace("${unix_timestamp}", String.valueOf(triggerTime.getTime() / 1000))
                .replace("${date}", String.valueOf(new DateTime().minusDays(1).toString("yyyy-MM-dd")));

        cycle = DateUtils.getDay10(triggerTime);
        String lastDay = DateUtils.getLastDay10(triggerTime);
        //todo
        logPath = new StringBuilder(Utilities.ParameterUtils.resourceParamHandle(task.getLogFile()))
                .append(File.separator).append(task.getLogFile().trim()).append(".")
                .append(instanceId).append(".").append(DateUtils.getDay8()).toString();
        calDt = DateUtils.get_cal_dt(lastDay, "offset", task.getOffset());

        InstanceDO inst = new InstanceDO();
        inst.setInstanceId(instanceId);
        inst.setTaskId(task.getTaskId());
        inst.setTaskGroupId(task.getTaskGroupId());
        inst.setTaskName(task.getTaskName());
        inst.setRunningPrio(null);

        inst.setCommand(command);
        inst.setLogPath(logPath);
        inst.setCycle(task.getCycle());
        inst.setTimeId(cycle);
        inst.setStatus(status);
        inst.setPriority(task.getPriority());
        inst.setRunNum(0);
        inst.setType(task.getType());
        inst.setResource(task.getResource());
        inst.setFreq(task.getFreq());
        inst.setCalDt(calDt);
        inst.setIfPre(task.getIfPre());
        inst.setRecallNum(0);
        inst.setOwner(task.getOwner());
        inst.setTriggerTime(triggerTime.getTime());
        inst.setRecallCode(task.getRecallCode());
        inst.setSuccessCode(task.getSuccessCode());
        inst.setIfWait(task.getIfWait());
        inst.setIfRecall(task.getIfRecall());
        inst.setWaitCode(task.getWaitCode());
        inst.setTimeout(task.getTimeout());
        inst.setRecallLimit(task.getRecallLimit());
        inst.setRecallInterval(task.getRecallInterval());
        inst.setTimestamp(currTime);
        inst.setJobCode(CoreConst.DEFAULT_TASK_JOBCODE);

        List<InstanceRelaDO> instRelaList = new ArrayList<InstanceRelaDO>();
        if (relaList == null) {
            return inst;
        }
        for (TaskRelaDO relaDO : relaList) {
            InstanceRelaDO instRela = new InstanceRelaDO();
            instRela.setInstanceId(instanceId);
            instRela.setTaskId(task.getTaskId());
            //String preInstanceId = TaskUtils.generateRelaInstanceID(relaDO.getPreId(), triggerTime.getTime(), relaDO.getCycleGap());
            //instRela.setPreInstanceId(preInstanceId);
            instRela.setPreId(relaDO.getPreId());
            instRela.setTimestamp(currTime);
            instRelaList.add(instRela);
        }
        inst.setInstRelaList(instRelaList);
        return inst;
    }


    class DynamicPriority {
        DynamicPriority(Integer rootId, Integer level, Map<Integer, TaskDO> taskDOMap, Map<Integer, List<TaskRelaDO>> reverseMap) {
            this.rootNodeId = rootId;
            if (level == 1) {
                point = midLimit + 1;
                limit = highLimit;
            } else if (level == 2) {
                point = lowLimit + 1;
                limit = midLimit;
            } else {
                point = 1;
                limit = lowLimit;
            }
            this.reverseMap = reverseMap;
            this.taskDOMap = taskDOMap;
        }

        private double point;
        private int limit;
        private int quotiety = 1;
        private int highLimit = 400;
        private int midLimit = 200;
        private int lowLimit = 50;
        private Integer rootNodeId = null;

        private Map<Integer, Integer> scoreMap = new HashMap<Integer, Integer>();
        private Map<Integer, List<TaskRelaDO>> reverseMap;
        private Map<Integer, TaskDO> taskDOMap;

        int calculateScore(int taskId, int prioLvl) {
            if (prioLvl < 1) {
                return 401;
            }
            if (prioLvl > 3) {
                return 0;
            }
            this.generateScoreMap(taskId);
            for (Integer prio : scoreMap.values()) {
                if (prio != null) {
                    point = point + 1d / prio * this.quotiety;
                }
            }
            if (point >= this.limit) {
                return this.limit;
            }
            return new Long(Math.round(point)).intValue();
        }

        private void generateScoreMap(Integer pk) {
            List<TaskRelaDO> relaList = reverseMap.get(pk);
            //not exists post task，avoid nullpointException
            if (CollectionUtils.isEmpty(relaList)) {
                return;
            }
            for (TaskRelaDO rela : relaList) {
                Integer childId = rela.getTaskId();
                if (this.rootNodeId == childId) {
                    return;
                }
                if (scoreMap.containsKey(childId)) {
                    return;
                }
                Integer prioLvl = null;
                try {
                    //avoid post task not exists
                    TaskDO task = taskDOMap.get(childId);
                    if (task == null) {
                        continue;
                    }
                    prioLvl = task.getPriority();
                } catch (Exception e) {
                    logger.error(childId + " task is invalid or offset if illegal");
                }
                this.scoreMap.put(childId, prioLvl);
                this.generateScoreMap(childId);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-applicationcontext-resource.xml");
        PreInitExecutor init = (PreInitExecutor) context.getBean("preInit");
        Date begin = DateFormatUtils.getFormatter().parse(args[0]);
        Date end = DateFormatUtils.getFormatter().parse(args[1]);
        init.execute(begin,end);
    }

}
