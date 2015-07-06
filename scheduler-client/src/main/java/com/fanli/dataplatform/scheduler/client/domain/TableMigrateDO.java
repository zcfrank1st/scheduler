package com.fanli.dataplatform.scheduler.client.domain;

import java.io.Serializable;

/**
 * Created by Sunny on 15/2/10.
 */
public class TableMigrateDO implements Serializable {
    private WormholeDO wormholeDO;
    private TaskDO taskDO;

    public TableMigrateDO() {
    }


    public WormholeDO getWormholeDO() {
        return wormholeDO;
    }

    public void setWormholeDO(WormholeDO wormholeDO) {
        this.wormholeDO = wormholeDO;
    }

    public TaskDO getTaskDO() {
        return taskDO;
    }

    public void setTaskDO(TaskDO taskDO) {
        this.taskDO = taskDO;
    }
}
