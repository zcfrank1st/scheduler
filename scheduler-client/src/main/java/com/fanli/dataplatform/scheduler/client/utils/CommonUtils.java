package com.fanli.dataplatform.scheduler.client.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sunny on 14-8-13.
 */
public class CommonUtils {

    public static int getDayDiff(Date date0, Date date1) {
        final int DAY_TO_MS = 1000 * 3600 * 24;
        int diff = (int) Math.floor(date0.getTime() - date1.getTime()) / DAY_TO_MS;
        return diff;
    }

    public static String getCurrentTimeStampStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTimeStamp = simpleDateFormat.format(new Date());
        return nowTimeStamp;
    }

    public static String getCurrentDateStr() {
        Calendar cal = Calendar.getInstance();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        return date;
    }

    public static Date strToDate(String strDate) {
        if (strDate.length() == 10)
            return strToDate(strDate, "yyyy-MM-dd");
        if (strDate.length() == 7)
            return strToDate(strDate, "yyyy-MM");
        else
            return strToDate(strDate, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date strToDate(String strDate, String format) {
        SimpleDateFormat simpleDateFormat;
        Date date;
        try {
            simpleDateFormat = new SimpleDateFormat(format);
            date = simpleDateFormat.parse(strDate);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    public static String dateToStr(Date date, String strFormat) {
        SimpleDateFormat format = new SimpleDateFormat(strFormat);
        return format.format(date);
    }
}
