#!/usr/bin/env bash
source /etc/profile

path="${deploy_home}/dwarch/bin/halley"

ps -ef | grep -i "java" |grep -i "com.fanli.dataplatform.scheduler.core.executor.StartScheduler" | awk '{print "kill "$2}' | sh

cd ${path}
sh ${path}/bin/start.sh
