package com.mysoft.alpha.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
    /**
     * 将日期字符转换为指定格式日期字符.缺省格式为yyyy-MM-dd
     *
     * @param dateStr    日期
     * @param dateFormat 日期格式
     * @return 按指定格式返回日期
     */
    public static String getDateByFormat(String dateStr, String dateFormat) {
        if (dateFormat == null || "".equals(dateFormat)) {
            dateFormat = "yyyy-MM-dd HH:mm:ss";
        }
        String str = "";
        try {
            if (dateStr != null && !"".equals(dateStr)) {
                dateStr = dateStr.replaceAll("年", "-");
                dateStr = dateStr.replaceAll("月", "-");
                dateStr = dateStr.replaceAll("日", "");
                dateStr = dateStr.replaceAll("/", "-");
                java.sql.Date dt = java.sql.Date.valueOf(dateStr);
                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                str = sdf.format(dt);
            } else {
                str = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 字符串日期转成Calendar
     *
     * @param sdate
     * @return Calendar
     */
    public static Calendar convertToCalendar(String sdate) {
        Calendar c = Calendar.getInstance();
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date dt = df.parse(sdate);
            c.setTime(dt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    /*
     * 字符串日期转成Date
     *
     * @param sdate
     *
     * @return java.util.Date
     */
    public static Date convertExcelToDate(String sdate, String format) {
        Date dt ;
        try {
            if (format.contains("yy")) {
                SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date dt1 = df1.parse("1900-01-01 00:00:00");
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(dt1);
                calendar.add(Calendar.DATE, Float.valueOf(sdate).intValue()-2);
                dt = calendar.getTime();
            } else {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                dt = df.parse(sdate);
            }
            return dt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Date convertExcelToDateBegin(String sdate, String format) {
        Date dt ;
        try {
            if (format.contains("yy")) {
                SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date dt1 = df1.parse("1900-01-01 00:00:00");
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(dt1);
                calendar.add(Calendar.DATE, Float.valueOf(sdate).intValue()-2);
                calendar.set(Calendar.HOUR, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                dt = calendar.getTime();
            } else {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                dt = df.parse(sdate);
            }
            return dt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Date convertExcelToDateEnd(String sdate, String format) {
        Date dt ;
        try {
            if (format.contains("yy")) {
                SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date dt1 = df1.parse("1900-01-01 00:00:00");
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(dt1);
                calendar.add(Calendar.DATE, Float.valueOf(sdate).intValue()-2);
                calendar.set(Calendar.HOUR, 23);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.SECOND, 59);
                dt = calendar.getTime();
            } else {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                dt = df.parse(sdate);
            }
            return dt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * 字符串日期转成Date
     *
     * @param sdate
     *
     * @return java.util.Date
     */
    public static Date convertToDate(String sdate) throws Exception {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date dt = df.parse(sdate);
            return dt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Date convertToDateTime(String sdate) throws Exception {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dt = df.parse(sdate);
            return dt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCurrentDate() {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dt = df.format(new Date());
            return dt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public static String getCurrentYearAndMonth() {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            String dt = df.format(new Date());            
            return dt.substring(0, 6);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Date addTime(Date date, int dateType, int addend) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(dateType, addend);
        return calendar.getTime();
    }    
}
