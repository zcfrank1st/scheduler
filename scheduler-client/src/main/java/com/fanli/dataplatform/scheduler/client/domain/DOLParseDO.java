package com.fanli.dataplatform.scheduler.client.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Sunny on 15-4-21.
 */
public class DOLParseDO implements Serializable, Cloneable {

    private String dolPath;

    private boolean isSuccess;

    private String message;

    private List<String> srcTableNames;

    private List<String> tarTableNames;

    private Map<String, List<TaskDO>> srcTableTaskMap;

    public DOLParseDO() {
    }

    public DOLParseDO(String message) {
        isSuccess = false;
        this.message = message;
    }

    public String getDolPath() {
        return dolPath;
    }

    public void setDolPath(String dolPath) {
        this.dolPath = dolPath;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getSrcTableNames() {
        return srcTableNames;
    }

    public void setSrcTableNames(List<String> srcTableNames) {
        this.srcTableNames = srcTableNames;
    }

    public List<String> getTarTableNames() {
        return tarTableNames;
    }

    public void setTarTableNames(List<String> tarTableNames) {
        this.tarTableNames = tarTableNames;
    }

    public Map<String, List<TaskDO>> getSrcTableTaskMap() {
        return srcTableTaskMap;
    }

    public void setSrcTableTaskMap(Map<String, List<TaskDO>> srcTableTaskMap) {
        this.srcTableTaskMap = srcTableTaskMap;
    }
}
