package com.fanli.dataplatform.scheduler.core.utils;

import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-applicationcontext-resource.xml")
public class TaskUtilsTest {

    @Test
    public void testGenerateInstanceID() throws Exception {
        String id = TaskUtils.generateInstanceID(10001,"D",new Date());
        String id1 = TaskUtils.generateInstanceID(10001,"M",new Date());
        String id2 = TaskUtils.generateInstanceID(10001,"W",new Date());
        String id3 = TaskUtils.generateInstanceID(10001,"mi",new Date());
        String id4 = TaskUtils.generateInstanceID(10001,"H",new Date());
        System.out.println(id);
        System.out.println(id1);
        System.out.println(id2);
        System.out.println(id3);
        System.out.println(id4);
    }

    @Test
    public void testGenerateRelaInstanceID() throws Exception {
        String preStsId = TaskUtils.generateRelaInstanceID(10002,"D",System.currentTimeMillis(),0);
        String preStsId1 = TaskUtils.generateRelaInstanceID(10002,"M",System.currentTimeMillis(),0);
        String preStsId2 = TaskUtils.generateRelaInstanceID(10002,"W",System.currentTimeMillis(),0);
        String preStsId3 = TaskUtils.generateRelaInstanceID(10002,"H",System.currentTimeMillis(),0);
        String preStsId4 = TaskUtils.generateRelaInstanceID(10002,"mi",System.currentTimeMillis(),0);
        System.out.println(preStsId);
        System.out.println(preStsId1);
        System.out.println(preStsId2);
        System.out.println(preStsId3);
        System.out.println(preStsId4);
    }
}