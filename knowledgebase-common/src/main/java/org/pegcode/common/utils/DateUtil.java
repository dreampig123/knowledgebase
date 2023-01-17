package org.pegcode.common.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil {

    public static final String PATTERN_yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static final String PATTERN_STANDARD = "yyyy-MM-dd HH:mm:ss";


    DateTimeFormatter df = null;
    /**
     * --------------------------LocalDateTime--------------------------
     */

    /**
     * localdatetime时间格式化为字符串
     *
     * @param time
     * @param pattern
     * @return
     */
    public String localDateTime2String(LocalDateTime time, String pattern) {
        df = DateTimeFormatter.ofPattern(pattern);
        return df.format(time);
    }

    /**
     * 时间字符串格式化为localdatetime时间
     *
     * @param timeStr
     * @param pattern
     * @return
     */
    public LocalDateTime string2LocalDateTime(String timeStr, String pattern) {
        df = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(timeStr, df);
    }

    /**
     * localdatetime时间格式化为目标格式
     *
     * @param time
     * @param targetPattern
     * @return
     */
    public LocalDateTime localDateTime2LocalDateTime(LocalDateTime time, String targetPattern) {
        df = DateTimeFormatter.ofPattern(targetPattern);
        String timeString = df.format(time);
        df = DateTimeFormatter.ofPattern(targetPattern);
        return LocalDateTime.parse(timeString, df);
    }

    /**
     * 判断时间是否在时间范围内
     *
     * @param time
     * @param startTime
     * @param endTime
     * @return
     */
    public boolean checkInTimeRange(LocalDateTime time, LocalDateTime startTime, LocalDateTime endTime) {
        if (time.isEqual(startTime) || time.isEqual(endTime)) {
            //等于开始时间或结束时间
            return true;
        }
        if (time.isAfter(startTime) && time.isBefore(endTime)) {
            //不包含等于开始时间和结束时间的情况
            return true;
        }
        return false;
    }

    /**
     * date转localdatetime
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    public static long currentTimestamp() {
        return System.currentTimeMillis();
    }
}
