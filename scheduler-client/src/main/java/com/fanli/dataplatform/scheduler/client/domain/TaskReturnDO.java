package com.fanli.dataplatform.scheduler.client.domain;

/**
 * Created by hongdi.tang on 14-5-4.
 */
public class TaskReturnDO {
    private Integer code;
    private String message;

    public TaskReturnDO(){

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
