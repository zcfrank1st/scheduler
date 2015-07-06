package com.fanli.dataplatform.scheduler.core.dao;

import com.fanli.dataplatform.scheduler.client.domain.TaskDO;
import com.fanli.dataplatform.scheduler.client.domain.TaskRelaDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by adima on 14-3-22.
 */
public interface TaskDAO {
    public List<TaskDO> getValidateTaskList(@Param("status") Integer status);

    public List<TaskRelaDO> getTaskRelaList();

}
