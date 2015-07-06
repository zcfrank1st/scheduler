package com.fanli.dataplatform.scheduler.client.domain;

import java.io.Serializable;

/**
 * Created by Sunny on 15/3/20.
 */
public class InstanceMonitorDO implements Serializable{
    private String taskStatusId;
    private Integer taskId;
    private String taskName;
    private Integer taskValue;
    private Integer endSlas;
    private String expectStartTime;
    private String predictEndTime;
    private Integer predictRunTime;
    private Integer isDelay;
    private Integer delaySeconds;
    private String startTime;
    private String endTime;
    private String timeId;
    private Integer postInstanceNum;
    private Integer averageExecuteMinutes;
    private Integer currentExecuteMinutes;

    public InstanceMonitorDO() {
    }

    public String getTaskStatusId() {
        return taskStatusId;
    }

    public void setTaskStatusId(String taskStatusId) {
        this.taskStatusId = taskStatusId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getExpectStartTime() {
        return expectStartTime;
    }

    public void setExpectStartTime(String expectStartTime) {
        this.expectStartTime = expectStartTime;
    }

    public Integer getTaskValue() {
        return taskValue;
    }

    public void setTaskValue(Integer taskValue) {
        this.taskValue = taskValue;
    }

    public Integer getEndSlas() {
        return endSlas;
    }

    public void setEndSlas(Integer endSlas) {
        this.endSlas = endSlas;
    }

    public Integer getPredictRunTime() {
        return predictRunTime;
    }

    public void setPredictRunTime(Integer predictRunTime) {
        this.predictRunTime = predictRunTime;
    }

    public Integer getIsDelay() {
        return isDelay;
    }

    public void setIsDelay(Integer isDelay) {
        this.isDelay = isDelay;
    }

    public Integer getDelaySeconds() {
        return delaySeconds;
    }

    public void setDelaySeconds(Integer delaySeconds) {
        this.delaySeconds = delaySeconds;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTimeId() {
        return timeId;
    }

    public void setTimeId(String timeId) {
        this.timeId = timeId;
    }

    public String getPredictEndTime() {
        return predictEndTime;
    }

    public void setPredictEndTime(String predictEndTime) {
        this.predictEndTime = predictEndTime;
    }

    public Integer getPostInstanceNum() {
        return postInstanceNum;
    }

    public void setPostInstanceNum(Integer postInstanceNum) {
        this.postInstanceNum = postInstanceNum;
    }

    public Integer getAverageExecuteMinutes() {
        return averageExecuteMinutes;
    }

    public void setAverageExecuteMinutes(Integer averageExecuteMinutes) {
        this.averageExecuteMinutes = averageExecuteMinutes;
    }

    public Integer getCurrentExecuteMinutes() {
        return currentExecuteMinutes;
    }

    public void setCurrentExecuteMinutes(Integer currentExecuteMinutes) {
        this.currentExecuteMinutes = currentExecuteMinutes;
    }
}
