package com.fanli.dataplatform.scheduler.client.client;

import com.fanli.dataplatform.scheduler.client.domain.TaskReturnDO;

import java.util.Map;

/**
 * Created by hongdi.tang on 14-4-3.
 */
public interface TaskClient {
    public TaskReturnDO run(Map<String, String> cmds);
}
