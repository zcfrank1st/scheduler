package com.fanli.dataplatform.scheduler.core.external;


import com.fanli.dataplatform.scheduler.client.domain.InstanceDO;
import com.fanli.dataplatform.scheduler.client.domain.TaskReturnDO;

/**
 * Created by hongdi.tang on 14-4-1.
 */

public interface ExternalExecuter {

    TaskReturnDO execute(InstanceDO inst);
}
