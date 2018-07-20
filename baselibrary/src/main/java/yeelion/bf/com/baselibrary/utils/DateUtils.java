package yeelion.bf.com.baselibrary.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Kurt on 2018/7/20 上午11:14
 * 时间工具类
 */

public class DateUtils {
    public static final String FORMAT_YMD = "yyyy-MM-dd";
    public static final String FORMAT_YMD_POINT = "yyyy.MM.dd";
    public static final String FORMAT_YMD_HM = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_YMD_HMS = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_YMD_CN = "yyyy年MM月dd日";
    public static final String FORMAT_YM_CN = "yyyy年MM月";
    public static final String FORMAT_HM = "HH:mm";
    public static final String FORMAT_MD = "MM-dd";

    public static final SimpleDateFormat chatDateFormat = new SimpleDateFormat(FORMAT_YMD_HMS);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat HMFormat = new SimpleDateFormat("HH:mm");
    private final static long minute = 60 * 1000;// 1分钟
    private final static long hour = 60 * minute;// 1小时


    public static String format(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    public static String format(long milliseconds, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(new Date(milliseconds));
    }


    /**
     * 获取两个日期之间的间隔天数
     *
     * @return
     */
    public static int getGapCount(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);
        return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }
}
