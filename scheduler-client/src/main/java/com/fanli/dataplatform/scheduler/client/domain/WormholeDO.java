package com.fanli.dataplatform.scheduler.client.domain;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by hongdi.tang on 14-2-14.
 */
public class WormholeDO implements Serializable {

    @NotNull
    private Integer taskId;
    @NotNull
    private String connectProps;

    private Map<String, Object> parameterMap;
    @NotNull
    private String parameterMapStr;

    @NotNull
    private String type;

    private String conditionCol;

    private String tableName;

    private String dbName;

    public WormholeDO() {

    }


    public String getConditionCol() {
        return conditionCol;
    }

    public void setConditionCol(String conditionCol) {
        this.conditionCol = conditionCol;
    }

    public String getParameterMapStr() {
        return parameterMapStr;
    }

    public void setParameterMapStr(String parameterMapStr) {
        this.parameterMapStr = parameterMapStr;
    }

    public void setConnectProps(String connectProps) {
        this.connectProps = connectProps;
    }

    public Map<String, Object> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<String, Object> parameterMap) {
        this.parameterMap = parameterMap;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getConnectProps() {
        return connectProps;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
