package com.fanli.dataplatform.scheduler.client.domain;

import java.io.Serializable;

/**
 * Created by adima on 14-3-22.
 */
public class InstanceRelaDO implements Serializable {
    private static final long serialVersionUID = 4649908676650113267L;
    private String instanceId;
    private Integer taskId;
    private String preInstanceId;
    private Integer preId;
    private String taskName;
    private String timestamp;

    public InstanceRelaDO() {

    }

    public InstanceRelaDO(String instanceId, Integer taskId, String taskName, String instancePreId, Integer taskPreId, String timeStamp) {
        this.instanceId = instanceId;
        this.taskId = taskId;
        this.taskName = taskName;
        this.preInstanceId = instancePreId;
        this.preId = taskPreId;
        this.timestamp = timeStamp;
    }


    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getPreInstanceId() {
        return preInstanceId;
    }

    public void setPreInstanceId(String preInstanceId) {
        this.preInstanceId = preInstanceId;
    }

    public Integer getPreId() {
        return preId;
    }

    public void setPreId(Integer preId) {
        this.preId = preId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
