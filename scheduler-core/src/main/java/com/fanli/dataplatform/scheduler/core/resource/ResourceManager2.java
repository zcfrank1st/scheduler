package com.fanli.dataplatform.scheduler.core.resource;

import com.fanli.dataplatform.scheduler.client.domain.InstanceDO;
import com.fanli.dataplatform.scheduler.core.common.GlobalResource;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by adima on 14-3-24.
 */
public class ResourceManager2 {
    private static Map<String, Resource> RESOURCE_QUEUE = new ConcurrentHashMap<String, Resource>();

    private static Logger logger = LoggerFactory.getLogger(ResourceManager2.class);
    private static Map<String, Process> processsMap = new ConcurrentHashMap<String, Process>();

    private ResourceManager2() {
    }

    ;

    private static class Resource {
        private String resourceName;
        private Integer capability;
        private Map<String, InstanceDO> queue;

        public Resource(String resourceName, Integer capability, Map<String, InstanceDO> queue) {
            this.resourceName = resourceName;
            this.capability = capability;
            this.queue = queue;
        }

        public Integer getCapability() {
            return capability;
        }

        public void setCapability(Integer capability) {
            this.capability = capability;
        }

        public String getResourceName() {
            return resourceName;
        }

        public void setResourceName(String resourceName) {
            this.resourceName = resourceName;
        }

        public synchronized Map<String, InstanceDO> getQueue() {
            return queue;
        }

        public void setQueue(Map<String, InstanceDO> queue) {
            this.queue = queue;
        }
    }

    static {
        try {
            String resourceProp = GlobalResource.ENV_PROPS.get("resource.properties").replace("${deploy_home}", GlobalResource.DEPLOY_HOME);
            PropertiesConfiguration config = new PropertiesConfiguration(resourceProp);
            Iterator<String> iter = config.getKeys();
            while (iter.hasNext()) {
                String key = iter.next();
                int value = config.getInt(key);
                RESOURCE_QUEUE.put(key, new Resource(key, value, new ConcurrentHashMap<String, InstanceDO>()));
            }
        } catch (Exception e) {
            logger.error("load resource error", e);
            throw new Error("load resource error");
        }
    }

    private static synchronized boolean outOfConcurrency(InstanceDO inst) {
        int currentConcurrency = 0;
        Map<String, InstanceDO> map = getAllInstance();
        for (InstanceDO value : map.values()) {
            if (value.getTaskId().intValue() == inst.getTaskId().intValue()) {
                currentConcurrency++;
            }
        }
        if (inst.getConcurrency() == null)
            inst.setConcurrency(1);
        return currentConcurrency >= inst.getConcurrency();
    }

    public static synchronized boolean idDupliateInstance(InstanceDO inst) {
        Map<String, InstanceDO> map = getAllInstance();
        return map.containsKey(inst.getInstanceId());
    }

    public static synchronized boolean removeProcess(String instanceId) {
        if (processsMap.containsKey(instanceId)) {
            processsMap.remove(instanceId);
            logger.info(" instanceid :" + instanceId + " has been removed from processMap");
        } else {
            logger.info(" instanceid :" + instanceId + " not exits processMap");
        }
        return true;
    }

    public static synchronized boolean addProcess(String instanceId, Process process) {
        processsMap.put(instanceId, process);
        logger.info(" instanceid :" + instanceId + " has been added to processMap");
        return true;
    }

    public static synchronized boolean destoryProcess(String instanceId) {
        if (processsMap.containsKey(instanceId)) {
            Process process = processsMap.get(instanceId);
            process.destroy();
            process.exitValue();
            processsMap.remove(instanceId);
            logger.info(" instanceid :" + instanceId + " has been removed from processMap");
        } else {
            logger.info(" instanceid :" + instanceId + " not exits processMap");
        }
        return true;
    }

    public static synchronized int inQueue(InstanceDO inst) {
        if (outOfConcurrency(inst)) {
            logger.info(inst.getInstanceId() + "(" + inst.getTaskName() + ") is beyond concurrency, concurrency is "
                    + inst.getConcurrency());
            return -1;
        }
        Resource resource = RESOURCE_QUEUE.get(inst.getConcurrency());
        if (resource == null) {
            resource = new Resource(inst.getResource(), 1, new ConcurrentHashMap<String, InstanceDO>());
            RESOURCE_QUEUE.put(inst.getResource(), resource);
            return 1;
        } else {
            if (resource.getQueue().size() < resource.capability) {
                resource.queue.put(inst.getInstanceId(), inst);
                return size(inst.getResource());
            } else {
                logger.info(inst.getResource() + " is full capability :=" + resource.getCapability());
                return -1;
            }
        }
    }

    public static synchronized int outQueue(InstanceDO inst) {
        RESOURCE_QUEUE.get(inst.getResource()).queue.remove(inst.getInstanceId());
        return size(inst.getResource());
    }

    private static Integer size(String resource) {
        Resource res = RESOURCE_QUEUE.get(resource);
        return res == null ? 0 : res.getQueue().size();
    }

    public static synchronized Integer size() {
        int result = 0;
        for (Resource res : RESOURCE_QUEUE.values()) {
            result = res.getQueue().size() + result;
        }
        return result;
    }

    private static synchronized Integer wormholeSize() {
        int result = 0;
        for (Resource res : RESOURCE_QUEUE.values()) {
            if (res.getResourceName().contains("hive")) {
                continue;
            }
            result = res.getQueue().size() + result;
        }
        return result;
    }

    public static Collection<InstanceDO> values(String resource) {
        Resource res = RESOURCE_QUEUE.get(resource);
        return res == null ? null : res.queue.values();
    }

    public static synchronized Collection<InstanceDO> values() {
        Collection<InstanceDO> list = new ArrayList<InstanceDO>();
        for (Resource runningQueue : RESOURCE_QUEUE.values()) {
            list.addAll(runningQueue.queue.values());
        }
        return list;
    }

    public static Set<Map.Entry<String, InstanceDO>> entrySet(String resource) {
        Resource res = RESOURCE_QUEUE.get(resource);
        return res == null ? null : res.queue.entrySet();
    }

    private static Map<String, InstanceDO> getAllInstance() {
        Map<String, InstanceDO> map = new ConcurrentHashMap<String, InstanceDO>();
        for (Resource res : RESOURCE_QUEUE.values()) {
            map.putAll(res.getQueue());
        }
        return map;
    }

}
