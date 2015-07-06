package com.fanli.dataplatform.scheduler.client.domain;

import java.io.Serializable;

public class TaskRelaDO implements Serializable {

    private static final long serialVersionUID = -1827386386229460099L;



    private Integer offset;
    private Integer taskId;
    private Integer preId;
    private String taskName;
    private String timeStamp;
    private String updateTime;
    private String owner;

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }


    public Integer getPreId() {
        return preId;
    }

    public void setPreId(Integer preId) {
        this.preId = preId;
    }

    public TaskRelaDO() {
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }


    public Integer getTaskId() {
        return taskId;
    }


    public String getTimeStamp() {
        return timeStamp;
    }


    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }


    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((offset == null) ? 0 : offset.hashCode());
        result = prime * result + ((owner == null) ? 0 : owner.hashCode());
        result = prime * result + ((taskId == null) ? 0 : taskId.hashCode());
        result = prime * result
                + ((taskName == null) ? 0 : taskName.hashCode());
        result = prime * result
                + ((preId == null) ? 0 : preId.hashCode());
        result = prime * result
                + ((timeStamp == null) ? 0 : timeStamp.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TaskRelaDO other = (TaskRelaDO) obj;
        if (offset == null) {
            if (other.offset != null)
                return false;
        } else if (!offset.equals(other.offset))
            return false;
        if (owner == null) {
            if (other.owner != null)
                return false;
        } else if (!owner.equals(other.owner))
            return false;
        if (taskId == null) {
            if (other.taskId != null)
                return false;
        } else if (!taskId.equals(other.taskId))
            return false;
        if (taskName == null) {
            if (other.taskName != null)
                return false;
        } else if (!taskName.equals(other.taskName))
            return false;
        if (preId == null) {
            if (other.preId != null)
                return false;
        } else if (!preId.equals(other.preId))
            return false;
        if (timeStamp == null) {
            if (other.timeStamp != null)
                return false;
        } else if (!timeStamp.equals(other.timeStamp))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TaskRelaDO [offset=" + offset
                + ", taskId=" + taskId + ", preId=" + preId
                + ", taskName=" + taskName + ", timeStamp=" + timeStamp
                + ", owner=" + owner + "]";
    }


}
