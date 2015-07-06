package com.fanli.dataplatform.scheduler.client.service;


import com.fanli.dataplatform.scheduler.client.domain.MonitorDO;
import com.fanli.dataplatform.scheduler.client.domain.UserDO;

/**
 * Created by Sunny on 14-8-12.
 */
public interface CommonService {

    /**
     * 获得当前值班人员
     */
    public MonitorDO getCurrentMonitor(UserDO userDO);

    /**
     * 尝试更新值班人员信息，发布会当前值班人员
     */
    public MonitorDO updateMonitor(UserDO user);

    /**
     * 判断用户是否是管理员
     */
    public boolean isAdmin(UserDO user);

    /**
     * 判断用户是否是值班人员
     */
    public boolean isMonitor(UserDO user);

}
