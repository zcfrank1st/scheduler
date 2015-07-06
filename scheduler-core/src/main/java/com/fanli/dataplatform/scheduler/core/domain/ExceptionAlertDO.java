package com.fanli.dataplatform.scheduler.core.domain;

/**
 * Created by Sunny on 14-7-18.
 */
public class ExceptionAlertDO {

    private Integer id;

    private String taskType;

    private String rule;

    private String description;

    private String oncall;

    private String product;

    public ExceptionAlertDO() {
    }

    public ExceptionAlertDO(Integer id, String taskType, String rule, String description, String oncall, String product) {
        this.id = id;
        this.taskType = taskType;
        this.rule = rule;
        this.product = product;
        this.description = description;
        this.oncall = oncall;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getOncall() {
        return oncall;
    }

    public void setOncall(String oncall) {
        this.oncall = oncall;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}
