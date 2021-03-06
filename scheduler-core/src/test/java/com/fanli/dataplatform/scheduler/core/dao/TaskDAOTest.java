package com.fanli.dataplatform.scheduler.core.dao;

import com.fanli.dataplatform.scheduler.client.domain.TaskDO;
import com.fanli.dataplatform.scheduler.client.domain.TaskRelaDO;
import com.fanli.dataplatform.scheduler.core.common.CoreConst;
import junit.framework.Assert;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by adima on 14-3-23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-applicationcontext-resource.xml")
public class TaskDAOTest {
    private Logger logger = LoggerFactory.getLogger(TaskDAOTest.class);

    @Resource(name="taskDAO")
    private TaskDAO dao;
    @org.junit.Test
    public void testGetValidateTaskList() throws Exception {
        List<TaskDO> list =  dao.getValidateTaskList(CoreConst.TASK_VALIDATE);
        Assert.assertNotNull(list);
    }

    @org.junit.Test
    public void testGetTaskRelaList() throws Exception {
        List<TaskRelaDO> list = dao.getTaskRelaList();
        Assert.assertNotNull(list);
    }
}
