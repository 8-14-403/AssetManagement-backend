package com.htjn.assetManagement.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by caojy on 2018/1/3.
 */
public class DateUtils {
    //获取当天的开始时间
    public static Long getDayBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }
    //获取当天的结束时间
    public static Long getDayEnd() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTimeInMillis();
    }
    //获取昨天的开始时间
    public static Long getBeginDayOfYesterday() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date(getDayBegin()));
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTimeInMillis();
    }
    //获取昨天的结束时间
    public static Long getEndDayOfYesterDay() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date(getDayEnd()));
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTimeInMillis();
    }

    //获取一周前的开始时间
    public static Long getBeginDayOfWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -7);
        return getDayStartTime(cal.getTime());
    }
/*    //获取本周的结束时间
    public static Long getEndDayOfWeek(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(getBeginDayOfWeek()));
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }*/
    //获取一月前的开始时间
    public static Long getBeginDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH,  - 1);
        return getDayStartTime(calendar.getTime());
    }
/*    //获取本月的结束时间
    public static Long getEndDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth() - 1, day);
        return getDayEndTime(calendar.getTime());
    }*/
    //获取一年前的开始时间
    public static Long getBeginDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, -1);
        return getDayStartTime(cal.getTime());
    }
/*    //获取本年的结束时间
    public static Long getEndDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear());
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DATE, 31);
        return getDayEndTime(cal.getTime());
    }*/
    //获取某个日期的开始时间
    public static Long getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }
/*    //获取某个日期的结束时间
    public static Long getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if(null != d) calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),    calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis();
    }*/
/*    //获取今年是哪一年
    public static Integer getNowYear() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return Integer.valueOf(gc.get(1));
    }
    //获取本月是哪一月
    public static int getNowMonth() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(2) + 1;
    }*/
}
