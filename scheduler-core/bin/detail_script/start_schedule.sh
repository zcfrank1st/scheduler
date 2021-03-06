#!/usr/bin/env bash
source /etc/profile

CURR_DIR=`pwd`
cd `dirname "$0"`/..
halley_home=`pwd`

run_path="${halley_home}/.."
echo $run_path
export LANG="zh_CN.utf8"
export conf_path="${run_path}/conf"
export cfg_props="conf.properties"

export deploy_home=/home/hadoop/scheduler-core

opt=" -Xmx4024M -Xss2048K"
cmd="${JAVA_HOME}/bin/java -classpath \"${run_path}/lib/*:${run_path}/conf/*\" ${opt} com.fanli.dataplatform.scheduler.core.executor.StartScheduler"

cd ${run_path}
process_num=`ps -ef | grep -i "com.fanli.dataplatform.scheduler.core.executor.StartScheduler" | grep -i java | grep -v grep | wc -l`
if [ $process_num -ne 1 ]
then
        ps -ef | grep -i "java" |grep -i "com.fanli.dataplatform.scheduler.core.executor.StartScheduler" | awk '{print "kill "$2}' | sh
        echo "the process will starts"
        echo ${cmd}
        eval ${cmd}
else
        echo "the process already run"
fi
exit