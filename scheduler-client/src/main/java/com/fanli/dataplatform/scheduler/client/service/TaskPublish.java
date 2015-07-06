package com.fanli.dataplatform.scheduler.client.service;

import com.fanli.dataplatform.scheduler.client.domain.DOLParseDO;
import com.fanli.dataplatform.scheduler.client.domain.TreeNodeDO;
import com.fanli.dataplatform.scheduler.client.domain.UserDO;

import java.util.List;

/**
 * Created by hongdi.tang on 14-6-9.
 */
public interface TaskPublish {

    /**
     * 任务发布
     * 返回DOL解析结果
     * 请在使用前保证fileName和projectName不为null
     */
    public DOLParseDO parseDOL(String projectName, String fileName, UserDO user);

    /**
     * 获得git目录树
     */
    public List<TreeNodeDO> getGitTree(UserDO user);

    /**
     * 发布git文件
     */
    public boolean publishGitFile(String projectName, String fileName, UserDO user);

    /**
     * 获得git文件内容
     */
    public String getGitFile(String fileName, UserDO user);
}
