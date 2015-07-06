package com.fanli.dataplatform.scheduler.client.service;

import com.fanli.dataplatform.scheduler.client.domain.*;

import java.util.List;

/**
 * Created by hongdi.tang on 14-2-11.
 */
public interface TaskService {

    /**
     * 根据taskId获得task
     */
    public TaskDO getTaskByTaskId(Integer taskId, UserDO user);

    /**
     * 根据taskIds获得task
     */
    public List<TaskDO> getTasksByTaskIds(List<Integer> taskIds, UserDO user);

    /**
     * 根据条件查询task
     */
    public List<TaskDO> queryTasks(TaskQueryDO taskQueryDO, UserDO user);

    /**
     * 预跑任务
     */
    public Integer preRunTask(PreRunDO preRunDO, UserDO user);

    /**
     * 按照时间顺序预跑任务
     */
    public boolean preRunJobAscend(Integer taskId, String begin, String end, UserDO user);

    /**
     * 删除任务
     */
    public boolean deleteTaskByTaskId(Integer id, String updateTime, UserDO user);

    /**
     * 新增任务
     */
    public boolean insertTask(TaskDO task, UserDO user);

    /**
     * 更新任务
     */
    public boolean updateTask(TaskDO task, UserDO user);

    /**
     * 检测是否存在环依赖
     */
    public boolean checkCycleDependence(TaskDO task, UserDO user);

    /**
     * 生效任务
     */
    public boolean validTaskByTaskId(Integer id, String updateTime, UserDO user);

    /**
     * 失效任务
     */
    public boolean invalidTaskByTaskId(Integer id, String updateTime, UserDO user);

    /**
     * 生成taskId
     */
    public Integer generateTaskId(TaskDO task, UserDO user);

    /**
     * 检测指定task name是否存在
     */
    public boolean checkTaskNameExists(String taskName, UserDO user);

    /**
     * 获得所有的task names
     */
    public List<String> getAllTaskNames(UserDO user);

    /**
     * 根据taskId获得其所有后续任务
     * 返回后续任务和自身所组成的list
     */
    public List<TaskDO> getPostTasksByTaskId(Integer taskId, UserDO user);

    /**
     * 获取级联重跑所有需要重跑的instance
     */
    public List<TaskRelaDO> getTaskRelasForCascadePreRun(Integer taskId, UserDO user);

    /**
     * 级联预跑
     */
    public boolean cascadePreRun(PreRunDO preRunDO, UserDO user);

    /**
     * 兼容接口
     */
    public TaskDO getTaskByTaskId(Integer taskId);

    /**
     * 兼容接口
     */
    public Integer preRunTask(Integer taskId, String begin, String end, String committer);

    /**
     * 停止预跑，将任务状态置为suspend
     * 返回状态受影响的instance数
     */
    public int batchStopTask(int taskId, UserDO user);

}
