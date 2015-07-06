package com.fanli.dataplatform.scheduler.core.executor;

import com.fanli.dataplatform.scheduler.client.client.Const;
import com.fanli.dataplatform.scheduler.core.dao.InstanceDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * Created by adima on 14-3-24.
 */
public class WaitExecutor {

    private static Logger logger = LoggerFactory.getLogger(WaitExecutor.class);
    @Resource(name="instanceDAO")
    private InstanceDAO instDAO;

    public void execute(){
        try{
            logger.info("the waitInit starts");
            Integer num = this.instDAO.updateInstnaceListStatus(Const.JOB_STATUS.JOB_INIT.getValue(),
                    Const.JOB_STATUS.JOB_WAIT.getValue());
            logger.info("wait executer update task num "+num);
        }finally{
            logger.info("the waitInit ends");
        }
    }

}
