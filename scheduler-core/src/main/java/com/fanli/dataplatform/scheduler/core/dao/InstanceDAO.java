package com.fanli.dataplatform.scheduler.core.dao;

import com.fanli.dataplatform.scheduler.client.domain.InstanceDO;
import com.fanli.dataplatform.scheduler.client.domain.InstanceRelaDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by adima on 14-3-23.
 */
public interface InstanceDAO {
    public List<InstanceDO> getFailInstanceList(@Param("status") Integer status);

    public void saveInstance(InstanceDO inst);

    public void saveInstanceRela(List<InstanceRelaDO> list);

    public InstanceDO getInstanceInfo(@Param("instanceId") String instanceId);

    public List<InstanceDO> getInitInstanceList(@Param("status") Integer status, @Param("triggerTime") Long triggerTime);

    public List<InstanceDO> getReadyInstanceList(@Param("status") Integer status);

    public List<InstanceDO> getReadyPreInstanceList(@Param("status") Integer status);

    public void updateInstnaceStatus(@Param("instanceId") String instanceId, @Param("status") Integer status);

    public void updateInstRecall(@Param("instanceId") String instanceId, @Param("status") Integer status, @Param("desc") String desc);

    public void updateInstEndStatus(@Param("instanceId") String instanceId,
                                    @Param("status") Integer status,
                                    @Param("time") String time,
                                    @Param("jobCode") Integer jobCode
                                    );

    public void updateInstnaceRunning(@Param("instanceId") String instanceId,
                                      @Param("status") Integer status,
                                      @Param("time") String time);

    public List<InstanceDO> getRelaInstanceList(@Param("instanceId") String instanceId);

    public Integer updateInstnaceListStatus(@Param("status") Integer initStatus,
                                            @Param("originStatus") Integer waitStatus);

    public Integer resetInstance(@Param("status") Integer initStatus,
                                 @Param("status1") Integer status1,
                                 @Param("status2") Integer status2);

}
