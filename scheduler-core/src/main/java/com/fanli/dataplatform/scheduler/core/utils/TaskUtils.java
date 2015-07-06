package com.fanli.dataplatform.scheduler.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by adima on 14-3-23.
 */
public class TaskUtils {
    private static Logger logger = LoggerFactory.getLogger(TaskUtils.class);
    private TaskUtils(){}
    private static SimpleDateFormat sdf = new SimpleDateFormat();

    public static String generateInstanceID(Integer task_id, String type, Date init_date)  {
        if (type.equals("H")) {
            sdf.applyPattern("yyyyMMddHH");
            return task_id + sdf.format(init_date);
        } else if (type.equals("D")) {
            sdf.applyPattern("yyyyMMdd00");
            return task_id + sdf.format(init_date);
        } else if (type.equals("W")) {
            sdf.applyPattern("yyyyww");
            return task_id + sdf.format(init_date);
        } else if (type.equals("M")) {
            sdf.applyPattern("yyyyMM0100");
            return task_id + sdf.format(init_date);
        } else if (type.equals("mi")) {
            sdf.applyPattern("yyyyMMddHHmm");
            return task_id + sdf.format(init_date);
        } else {
            throw new IllegalArgumentException("\""+type +"\" is illegal for the type of cycle ");
        }
    }

    public static String generateRelaInstanceID(Integer pre_id,String cycle, Long fire_time, Integer offset){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(fire_time);
        Date date = calendar.getTime();

        if (cycle.equals("H")) {
            sdf.applyPattern("yyyyMMddHH");
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.HOUR, -1 * offset);
            String pre_str_date = sdf.format(cal.getTime());

            return pre_id + pre_str_date;
        } else if (cycle.equals("D")) {
            sdf.applyPattern("yyyyMMdd00");
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, -1 * offset);

            String pre_str_date = sdf.format(cal.getTime());
            return pre_id + pre_str_date;
        } else if (cycle.equals("M")) {
            sdf.applyPattern("yyyyMM0100");
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MONTH, -1 * offset);
            String pre_str_date = sdf.format(cal.getTime());
            return pre_id + pre_str_date;
        } else if (cycle.equals("W")) {
            sdf.applyPattern("yyyyww");
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.WEEK_OF_YEAR, -1 * offset);
            String pre_str_date = sdf.format(cal.getTime());
            return pre_id + pre_str_date;
        } else if (cycle.equals("mi")) {
            sdf.applyPattern("yyyyMMddHHmm");
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.WEEK_OF_YEAR, -1 * offset);
            String pre_str_date = sdf.format(cal.getTime());
            return pre_id + pre_str_date;
        } else {
            logger.error("error input cycle gap " + offset);
            return null;
        }
    }
}
