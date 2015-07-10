#!/usr/bin/env bash
source /etc/profile

path="${deploy_home}/dwarch/bin/halley"

ps -ef | grep -i "java" |grep -i "com.dianping.main.ScheduleMain" | awk '{print "kill "$2}' | sh

cd ${path}
sh ${path}/bin/start.sh
