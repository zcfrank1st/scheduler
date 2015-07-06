package com.fanli.dataplatform.scheduler.core.executor;

import com.fanli.dataplatform.scheduler.client.client.Const;
import com.fanli.dataplatform.scheduler.client.domain.InstanceDO;
import com.fanli.dataplatform.scheduler.core.dao.InstanceDAO;
import com.fanli.dataplatform.scheduler.core.resource.ResourceManager2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * Created by hongdi.tang on 14-3-24.
 */
public class SynchronizeExecutor {
    private static final Logger logger = LoggerFactory.getLogger(SynchronizeExecutor.class);

    @Resource
    private InstanceDAO instDAO;

    public void execute() {
        try {
            logger.info("the SynchronizeExecutor thread starts");
            for (InstanceDO inst : ResourceManager2.values()) {
                logger.info(inst.getInstanceId() + "(" + inst.getTaskName() +
                        ") in queue time " + inst.getInQueueTimeMillis());
                try{
                    Integer status = instDAO.getInstanceInfo(inst.getInstanceId()).getStatus();
                    if (status != Const.JOB_STATUS.JOB_RUNNING.getValue() &&
                            status != Const.JOB_STATUS.JOB_TIMEOUT.getValue()) {
                        logger.info(inst.getInstanceId() + "(" + inst.getTaskName() + ") status is, " + inst.getStatus() +
                                " in queue time at" + String.valueOf(inst.getInQueueTimeMillis()) + " has been removed");
                        ResourceManager2.destoryProcess(inst.getInstanceId());
                        ResourceManager2.outQueue(inst);
                    }
                }catch(Exception e){
                    logger.error(inst.getInstanceId() + "(" + inst.getTaskName() + ") synchronize error",e);
                }
            }
        } finally {
            logger.info("the synchronize thread ends");
        }
    }

}
