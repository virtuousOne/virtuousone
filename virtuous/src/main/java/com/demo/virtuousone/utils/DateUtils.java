package com.demo.virtuousone.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @Auther: 吴宸煊
 * @Date: 2019/1/14
 * @Description: 时间的转换工具
 */
public class DateUtils {
    /**
     * Java的 1.8 时间有了新的特效
     * LocalTime :  只包括时间 ；  数据库对应的是 time   格式： 11:47:13
     * LocalDate : 只包括日期；    数据库对应的时间是： date  格式： 2019-01-14
     * LocalDateTime ： 包括日期和时间;  数据库对应的时间是 timestamp  格式：2019-01-14T11:47:13.549
     */
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat dateFormatDB = new SimpleDateFormat("yyyyMMdd");// 数据库使用的日期格式

    /**
     * 把LocalDateTime 转为 String 类型
     *
     * @param localDateTime
     * @return
     */
    public String LocalDateTimeToString(LocalDateTime localDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String stringDateTime = dateTimeFormatter.format(localDateTime);
        return stringDateTime;
    }

    /**
     * 把String类型 转为 LocalDateTime
     *
     * @param stringDateTime
     * @return
     */
    public LocalDateTime StringToLocalDateTime(String stringDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(stringDateTime, dateTimeFormatter);
        return localDateTime;
    }

    /**
     * 把LocalDate 转为 String 类型
     *
     * @param localDate
     * @return
     */
    public String LocalDateToString(LocalDate localDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String stringDateTime = dateTimeFormatter.format(localDate);
        return stringDateTime;
    }

    /**
     * 把String类型 转为 LocalDate
     *
     * @param stringDate
     * @return
     */
    public LocalDateTime StringToLocalDate(String stringDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime localDateTime = LocalDateTime.parse(stringDate, dateTimeFormatter);
        return localDateTime;
    }

    /**
     * 把LocalTime 转为 String 类型
     *
     * @param localTime
     * @return
     */
    public String LocalTimeToString(LocalTime localTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String stringDateTime = dateTimeFormatter.format(localTime);
        return stringDateTime;
    }

    /**
     * 把String 类型 转为 LocalTime
     *
     * @param stringTime
     * @return
     */
    public LocalTime StringToLocalTime(String stringTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.parse(stringTime, dateTimeFormatter);
        return localTime;
    }

    /**
     * 功能：格式化日期字符串
     * 例如：2018-8-8  转换为2018-08-08
     *
     * @param dateStr
     * @return
     */
    public static String getDateStrFormat(String dateStr) {
        try {
            Date date = dateFormat.parse(dateStr);
            return dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能：把日期yyyy-MM-dd格式转换成yyyyMMDD日期格式
     *
     * @param dateStr
     * @return
     */
    public static String convertClientDateToDbDate(String dateStr) {
        String dbDateStr = null;
        try {
            dbDateStr = dateFormatDB.format(dateFormat.parse(dateStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbDateStr;
    }

    /**
     * 功能：返回年
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);

    }

    /**
     * 功能：返回月
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 功能：返回日
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 功能：获取当前月的第一天日期
     *
     * @return
     */
    public static Date getMonFirstDay() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.set(getYear(date), getMonth(date) - 1, 1);
        return c.getTime();
    }

    /**
     * 功能：获取当前月的最后一天日期
     *
     * @return
     */
    public static Date getMonLastDay() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.set(getYear(date), getMonth(date), 1);
        c.setTimeInMillis(c.getTimeInMillis() - (24 * 3600 * 1000));
        return c.getTime();
    }

    /**
     * 功能：获取上个月的最后一天日期
     *
     * @return
     */
    public static Date getMonUpDay() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.set(getYear(date), getMonth(date) - 1, 1);
        c.setTimeInMillis(c.getTimeInMillis() - (24 * 3600 * 1000));
        return c.getTime();
    }

    /**
     * 是否是闰年
     */
    private static boolean isLeapYear(int year) {
        return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
    }

    /**
     * 验证输入的日期是否合法
     *
     * @param expDate
     * @return
     */
    public static boolean checkExpDate(String expDate) {
        int year = Integer.parseInt(expDate.substring(0, 4));
        int month = Integer.parseInt(expDate.substring(4, 6));
        int day = Integer.parseInt(expDate.substring(6, 8));
        if (month > 12 || month < 1) {
            return false;
        }

        int[] monthLengths = new int[]{0, 31, -1, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (isLeapYear(year)) {
            monthLengths[2] = 29;
        } else {
            monthLengths[2] = 28;
        }

        int monthLength = monthLengths[month];
        if (day < 1 || day > monthLength) {
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();
        String localTime = df.format(time);
        LocalDateTime ldt = LocalDateTime.parse("2018-01-12 17:07:05", df);
        System.out.println("LocalDateTime转成String类型的时间：" + localTime);
        System.out.println("String类型的时间转成LocalDateTime：" + ldt);
    }

}
