source /etc/profile

#path="/data/deploy/dwarch/bin/Scheduling/bin"
ps -ef | grep -i "java" |grep -i "com.dianping.main.ScheduleMain" | awk '{print "kill "$2}' | sh

