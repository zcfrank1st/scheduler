source /etc/profile

run_path="${deploy_home}/halley"

export LANG="zh_SG.gb2312"
export conf_path="${run_path}/conf"
export cfg_props="conf.properties"

function usage(){
	echo "Example:"		
	echo "sh /data/deploy/halley/runJob.sh -id 80001 -begin 2012-01-01 -end 2012-05-01"
}

function main(){
	opt=" -Xmx3024M -Xss2048K"
	cmd="${JAVA_HOME}/bin/java -classpath \"${run_path}/lib/*:${run_path}/conf/*\" ${opt} com.dianping.main.HistoryInitMain $@"
	cd ${run_path}
	if [ $# -ne 6 ];then
		echo -e "the input parameters are error!"
		useage
		exit
	fi
	echo ${cmd}
	eval ${cmd}
}

eval main $@









