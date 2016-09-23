package com.bystanders.moeta.ones.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 获得事件，转化时间
 * Created by Ishinagi_moeta on 2016/9/20.
 */
public class TimeUtils {

    /**
     * XXXX-XX-XX XX:XX:XX转成 EEE d MMM.yyyy
     */
    public static String time2data(String time) {
        String[] strData = time.split(" ");
        String ymd[] = strData[0].split("-");
        String hms[] = strData[1].split(":");
        int year = Integer.parseInt(ymd[0]);
        int mouth = Integer.parseInt(ymd[1]) - 1;
        int day = Integer.parseInt(ymd[2]);

        int hour = Integer.parseInt(hms[0]);
        int minute = Integer.parseInt(hms[1]);
        int second = Integer.parseInt(hms[2]);
        Calendar calendar = new GregorianCalendar(year, mouth, day, hour, minute, second);
        Date date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE d MMM.yyyy", Locale.ENGLISH);
        return dateFormat.format(date);
    }

    /**
     * XXXX-XX-XX XX:XX:XX转成  d MMM.yyyy
     */
    public static String time2data_var2(String time) {
        String[] strData = time.split(" ");
        String ymd[] = strData[0].split("-");
        String hms[] = strData[1].split(":");
        int year = Integer.parseInt(ymd[0]);
        int mouth = Integer.parseInt(ymd[1]) - 1;
        int day = Integer.parseInt(ymd[2]);

        int hour = Integer.parseInt(hms[0]);
        int minute = Integer.parseInt(hms[1]);
        int second = Integer.parseInt(hms[2]);
        Calendar calendar = new GregorianCalendar(year, mouth, day, hour, minute, second);
        Date date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM.yyyy", Locale.ENGLISH);
        return dateFormat.format(date);
    }
}
