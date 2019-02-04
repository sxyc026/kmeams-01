package com.algorithm.kmeams01.common.Utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Jsb3006
 * @Title: DateUtils
 * @ProjectName tax
 * @Description: TODO
 * @date 2019/1/2515:45
 */
@Slf4j
public class DateUtils {


    private static final String pattern = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当月第一天
     *
     * @return
     */
    public static Date getFirstDay() {
        return processDate(null, null);
    }

    /**
     * 获取指定日期当月第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDay(Date date) {
        return processDate(date, null);
    }

    /**
     * 从字符串获取第一天时间
     *
     * @param date
     * @return
     */
    public static Date getFirstDayFromString(String date) {
        Date dateInner = processString2Date(date, null);
        dateInner = processDate(dateInner, null);
        return dateInner;
    }

    /**
     * 从指定时间获取当月第一天，返回指定格式
     *
     * @param date
     * @param dateFormat
     * @return
     */
    public static Date getFirstDayFromString(String date, String dateFormat) {
        Date dateInner = processString2Date(date, dateFormat);
        dateInner = processDate(dateInner, dateFormat);
        return dateInner;
    }

    /**
     * 将第一天时间转换为字符串
     *
     * @return
     */
    public static String getFirstDay2String() {
        Date date = getFirstDay();
        return Date2String(date);

    }

    /**
     * 将第一天时间转换为指定格式的字符串
     *
     * @param format
     * @return
     */
    public static String getFirstDay2String(String format) {
        Date date = getFirstDay();
        return Date2String(date, format);
    }


    /**
     * 将指定时间转换为当月第一天的字符串
     *
     * @param date
     * @return
     */
    public static String getFirstDay2String(Date date) {
        date = getFirstDay(date);
        return Date2String(date);

    }


    /**
     * 将指定时间第一天转换为相应格式字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String getFirstDay2String(Date date, String format) {
        date = getFirstDay(date);
        return Date2String(date, format);

    }

    /**
     * Date 格式化处理
     *
     * @param date
     * @param dateFormat
     * @return
     */
    private static Date processDate(Date date, String dateFormat) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        date = calendar.getTime();
        if (!(dateFormat == null || dateFormat.isEmpty())) {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            try {
                date = simpleDateFormat.parse(simpleDateFormat.format(date));
            } catch (ParseException e) {
                log.info("Date Format Failed");
            }
        }
        return date;
    }

    /**
     * 时间转日期
     * 将指定日期转换为字符串
     *
     * @param date
     * @return
     */
    public static String Date2String(Date date) {

        return processDate2String(date, null);

    }

    /**
     * 时间转日期
     *
     * @param date
     * @return
     */
    public static String Date2String(Date date, String format) {
        return processDate2String(date, format);


    }

    /**
     * 时间转日期
     *
     * @return
     */
    public static String Date2String() {
        return processDate2String(null, null);

    }

    /**
     * 时间转日期
     *
     * @return
     */
    public static String Date2String(String format) {
        return processDate2String(null, format);

    }

    /**
     * 字符串转日期
     *
     * @param date
     * @param dateFormat
     * @return
     */
    private static Date processString2Date(String date, String dateFormat) {

        Date dateInner = new Date();
        if (date.isEmpty()) {
            return new Date();
        }

        if (dateFormat.isEmpty()) {
            dateFormat = pattern;
        }

        try {
            dateInner = new SimpleDateFormat(dateFormat).parse(date);
        } catch (ParseException e) {
            log.info("Date Format Failed!");
        }
        return dateInner;

    }

    /**
     * 时间转换为特殊格式的字符串
     *
     * @param date
     * @param format
     * @return
     */
    private static String processDate2String(Date date, String format) {
        if (format == null || format.isEmpty()) {
            format = pattern;
        }

        if (date == null) {
            date = new Date();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);

    }


}
