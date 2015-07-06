package com.fanli.dataplatform.scheduler.client.service;


import com.fanli.dataplatform.scheduler.client.domain.TableMigrateDO;
import com.fanli.dataplatform.scheduler.client.domain.UserDO;
import com.fanli.dataplatform.scheduler.client.domain.WormholeDO;

import java.util.List;

/**
 * Created by Sunny on 15/2/9.
 */
public interface TransferService {

    /**
     * 根据任务id获取相关的传输配置信息
     */
    public List<WormholeDO> getLoadConfigsByTaskId(int taskId, UserDO user);

    /**
     * 根据任务id获取相关的传输读配置信息
     */
    public WormholeDO getReaderLoadConfigByTaskId(int taskId, UserDO user);

    /**
     * 插入传输配置信息
     */
    public boolean insertLoadConfigs(List<WormholeDO> wormholeDOs, UserDO user);

    /**
     * 删除任务id对应的传输配置信息
     */
    public boolean deleteLoadConfigsByTaskId(int taskId, UserDO user);

    /**
     * 更新任务id对应的传输配置信息
     */
    public boolean updateLoadConfigsByTaskId(int taskId, List<WormholeDO> wormholeDOs, UserDO user);

    /**
     * 根据数据库名查询表迁移涉及到的表
     */
    public List<TableMigrateDO> getTableMigrateDOsByDBName(String dbName, UserDO user);

    /**
     * 迁移表修改相关配置信息
     */
    public boolean migrateTable(List<Integer> taskIds, String dbName, UserDO user);

    /**
     * 根据数据库名获取相关的规则信息
     */
    public List<String> getRulesByDBName(String dbName, UserDO user);

    /**
     * 获取mysql库的url信息
     */
    public String getMysqlDatabaseURL(String dbName, UserDO user);

    /**
     * 获取mysql库的id信息
     */
    public Integer getMysqlDatabaseId(String dbName, UserDO user);

}
