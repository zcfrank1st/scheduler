package com.fanli.dataplatform.scheduler.core.executor;

import com.fanli.dataplatform.scheduler.client.client.Const;
import com.fanli.dataplatform.scheduler.core.dao.InstanceDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * Created by hongdi.tang on 14-3-28.
 */
public class ResetExecutor {
    @Resource(name="instanceDAO")
    private InstanceDAO instDAO;

    private static Logger logger = LoggerFactory.getLogger(ResetExecutor.class);
    public void execute(){
        try{
            logger.info("the reset executer starts");
            Integer num = this.instDAO.resetInstance(Const.JOB_STATUS.JOB_INIT.getValue(),
                    Const.JOB_STATUS.JOB_RUNNING.getValue(),
                    Const.JOB_STATUS.JOB_TIMEOUT.getValue());
            logger.info("reset update task num "+num);
        }finally{
            logger.info("the reset executer ends");
        }
    }
}
