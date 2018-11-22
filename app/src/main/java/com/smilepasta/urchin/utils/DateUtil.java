package com.smilepasta.urchin.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * TimeUtils
 */
public class DateUtil {

    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");

    private DateUtil() {
        throw new AssertionError();
    }

    /**
     * long time to string
     *
     * @param timeInMillis
     * @param dateFormat
     * @return
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @param timeInMillis
     * @return
     */
    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
    }

    /**
     * long time to string, format is {@link #DATE_FORMAT_DATE}
     *
     * @param timeInMillis
     * @return
     */
    public static String getDate(long timeInMillis) {
        return getTime(timeInMillis, DATE_FORMAT_DATE);
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /**
     * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @return
     */
    public static String getCurrentTimeInString() {
        return getTime(getCurrentTimeInLong());
    }

    /**
     * get current time in milliseconds, format is {@link #DATE_FORMAT_DATE}
     *
     * @return
     */
    public static String getCurrentDateInString() {
        return getDate(getCurrentTimeInLong());
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
        return getTime(getCurrentTimeInLong(), dateFormat);
    }

    /**
     * 转换时间日期格式字串为long型
     *
     * @param time 格式为：yyyy-MM-dd的时间日期类型
     */
    public static Long convertTimeToLong(String time) {
        Date date = null;
        try {
            date = DATE_FORMAT_DATE.parse(time);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    /**
     * 将yyyy-MM-dd HH:mm转换成yyyy-MM-dd返回
     */
    public static String extractDate(String date) {
        String[] arr = date.split(" ");
        if (arr.length == 2) {
            return arr[0];
        } else {
            return date;
        }
    }

    public static int getPageDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        //加一天，取的是当前时间之前的新闻，并且要包括当天
        return Integer.parseInt(getTime(getCurrentTimeInLong() + (24 * 60 * 60 * 1000), simpleDateFormat));
    }

    public static int getDecrementPageDate(int pageDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = simpleDateFormat.parse(pageDate + "");
            Calendar calendar = Calendar.getInstance(); //得到日历
            calendar.setTime(date);//把当前时间赋给日历
            calendar.add(Calendar.DAY_OF_MONTH, -1);  //算前一天
            Date beforeDate = calendar.getTime();
            return Integer.parseInt(simpleDateFormat.format(beforeDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}