package com.fanli.dataplatform.scheduler.core.executor;

import com.fanli.dataplatform.scheduler.client.client.Const;
import com.fanli.dataplatform.scheduler.client.domain.InstanceDO;
import com.fanli.dataplatform.scheduler.core.dao.InstanceDAO;
import com.fanli.dataplatform.scheduler.core.resource.ResourceManager2;
import com.fanli.dataplatform.scheduler.core.utils.DateFormatUtils;
import com.fanli.dataplatform.scheduler.core.utils.ExceptionAnalyze;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by adima on 14-3-24.
 */
public class InstanceExecutor {
    private static Logger logger = LoggerFactory.getLogger(InstanceExecutor.class);
    @Resource
    private InstanceDAO instDAO;

    private ThreadPoolTaskExecutor taskExecutor;

    @Resource
    private ExceptionAnalyze exceptionAnalyze;

    public InstanceExecutor(ThreadPoolTaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public void execute() throws Exception{
        try{
            logger.info("the execute thread starts");
            List<InstanceDO> list = this.instDAO.getReadyInstanceList(Const.JOB_STATUS.JOB_READY.getValue());
            if (CollectionUtils.isEmpty(list)) {
                return;
            }

            for (InstanceDO inst : list) {
                if (inst == null) {
                    continue;
                }
                try{
                    int size = ResourceManager2.inQueue(inst);
                    if(size==-1){
                        logger.info(inst.getInstanceId() + "(" + inst.getTaskName() + ") resource:= "+inst.getResource() +
                                " score:= "+inst.getRunningPrio()+
                                " not join to queue");
                        continue;
                    }
                    logger.info(inst.getConcurrency() +" " +size);

                    inst.setInQueueTimeMillis(System.currentTimeMillis());
                    logger.info(inst.getInstanceId() + "(" + inst.getTaskName() + ") resource:= "+inst.getResource() +
                            " score:= "+inst.getRunningPrio()+
                            " join to Running Queue");

                    String currTime = DateFormatUtils.getFormatter().format(new Date());
                    this.instDAO.updateInstnaceRunning(inst.getInstanceId(), Const.JOB_STATUS.JOB_RUNNING.getValue(),
                             currTime);

                    Task2 task = new Task2(instDAO,inst,exceptionAnalyze);
                    Thread.sleep(2);
                    taskExecutor.submit(task);
                }catch(Exception e){
                    logger.error("instance execute error",e);
                }
            }
        }finally{
            logger.info("the execute thread ends");
        }


    }
}
