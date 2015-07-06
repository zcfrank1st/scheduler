source /etc/profile

#path=`pwd`
path="${home}/scheduler"
log_path="${deploy_home}/log/Scheduer"

nohup sh ${path}/bin/detail_script/start_schedule.sh &
