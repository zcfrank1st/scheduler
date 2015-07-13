#!/usr/bin/env bash
source /etc/profile

#path="/data/deploy/dwarch/bin/Scheduling/bin"
ps -ef | grep -i "java" |grep -i "com.fanli.dataplatform.scheduler.core.executor.StartScheduler" | awk '{print "kill "$2}' | sh

