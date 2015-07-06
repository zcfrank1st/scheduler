package com.fanli.dataplatform.scheduler.core.executor;

import com.fanli.dataplatform.scheduler.client.client.Const;
import com.fanli.dataplatform.scheduler.client.domain.InstanceDO;
import com.fanli.dataplatform.scheduler.core.dao.InstanceDAO;
import com.fanli.dataplatform.scheduler.core.resource.ResourceManager2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * Created by adima on 14-3-24.
 */
public class TimeoutExecutor {
    private static Logger logger = LoggerFactory.getLogger(TimeoutExecutor.class);


    @Resource(name="instanceDAO")
    private InstanceDAO instDAO;

    public void execute(){
        try{
            logger.info("the timeout execute process starts");
            for (InstanceDO inst : ResourceManager2.values()) {
                try {
                    Long startTime = inst.getInQueueTimeMillis();
                    Long currentTime = System.currentTimeMillis();
                    if (currentTime - startTime > inst.getTimeout() * 60 * 1000) {
                        logger.info(inst.getInstanceId()+"(" +inst.getTaskName()+") is timeout" +
                                " inqueue time ="+ startTime + " timeout :="+inst.getTimeout() +" minutes");
                        this.instDAO.updateInstnaceStatus(inst.getInstanceId(), Const.JOB_STATUS.JOB_TIMEOUT.getValue());
                    }
                } catch (Exception e) {
                    logger.error(inst.getInstanceId()+"("+inst.getTaskName()+") set timeout error", e);
                }
            }
        }finally{
            logger.info("the timeout execute process ends");
        }
    }
}
