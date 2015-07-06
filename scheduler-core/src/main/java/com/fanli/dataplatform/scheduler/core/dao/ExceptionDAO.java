package com.fanli.dataplatform.scheduler.core.dao;


import com.fanli.dataplatform.scheduler.core.domain.ExceptionAlertDO;

import java.util.List;

/**
 * Created by Sunny on 14-7-30.
 */
public interface ExceptionDAO {

    public List<ExceptionAlertDO> getExceptionAlertsByTaskType(String taskType);

}
