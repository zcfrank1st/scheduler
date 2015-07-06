package com.fanli.dataplatform.scheduler.client.domain;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserDO implements Serializable {
    @NotNull(message = "loginId is not null")
    private Integer loginId;
    @NotNull(message = "employPinyin is not null")
    private String employPinyin;
    @NotNull(message = "employeeId is not null")
    private String employeeId;
    @NotNull(message = "employeeName is not null")
    private String employeeName;
    @NotNull(message = "employeeEmail is not null")
    private String employeeEmail;

    public UserDO() {

    }

    public UserDO(Integer loginId, String employPinyin, String employeeId, String employeeName, String employeeEmail) {
        this.loginId = loginId;
        this.employPinyin = employPinyin;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
    }

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployPinyin() {
        return employPinyin;
    }

    public void setEmployPinyin(String employPinyin) {
        this.employPinyin = employPinyin;
    }

    public String toString() {
        return employeeId + "|" + loginId + "|" + employPinyin + "|" + employeeName;
    }
}
