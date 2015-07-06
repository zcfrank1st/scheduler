package com.fanli.dataplatform.scheduler.client.service;

/**
 * Created by Sunny on 14-8-12.
 */
public interface MonitorService {

    /**
     * 兼容接口
     */
    public boolean isAdmin(int loginId);

    /**
     * 兼容接口
     */
    public boolean isMonitor(String pinyinName);

}