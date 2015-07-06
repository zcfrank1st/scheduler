package com.fanli.dataplatform.scheduler.client.domain;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sunny on 14/12/4.
 */
public class PreRunInstanceQueryDO implements Serializable{
    private String startTime;
    private String committer;
    private List<String> taskIds;
    private String taskName;

    public PreRunInstanceQueryDO() {
    }


    public PreRunInstanceQueryDO(String startTime, String committer, String taskNameOrId) {
        this.startTime = startTime;
        this.committer = committer;
        if (!StringUtils.isBlank(taskNameOrId)) {
            if (isTaskName(taskNameOrId)) {
                taskName = taskNameOrId;
            } else {
                String[] taskNameOrIds = taskNameOrId.split(",");
                this.taskIds = Arrays.asList(taskNameOrIds);
                //指定id下其他搜索条件默认为不限
                this.committer = null;
            }
        }
    }

    public String getCommitter() {
        return committer;
    }

    public void setCommitter(String committer) {
        this.committer = committer;
    }

    public List<String> getTaskIds() {
        return taskIds;
    }

    public void setTaskIds(List<String> taskIds) {
        this.taskIds = taskIds;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * 判断传入参数是taskName还是taskIds
     */
    private boolean isTaskName(String taskNameOrId) {
        if (taskNameOrId.indexOf(',') != -1)
            return false;
        for (int i = 0; i < taskNameOrId.length(); i++)
            if (taskNameOrId.charAt(i) < '0' || taskNameOrId.charAt(i) > '9')
                return true;
        return false;
    }
}
