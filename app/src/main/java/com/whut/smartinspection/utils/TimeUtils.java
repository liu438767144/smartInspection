package com.whut.smartinspection.utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by lyz on 2018/4/3.
 */

public class TimeUtils {

    public static String setCurrentTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);//年-月-日
        Date date = new Date(System.currentTimeMillis());//获取当前时间
        String currentTime = simpleDateFormat.format(date);
        return currentTime;
    }

    public static String setCurrentTime2() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);//年-月-日 时:分
        Date date = new Date(System.currentTimeMillis());
        String currentTime = simpleDateFormat.format(date);
        return currentTime;
    }

    public static String setCurrentTime3() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);//年-月-日 时:分:秒
        Date date = new Date(System.currentTimeMillis());
        String currentTime = simpleDateFormat.format(date);
        return currentTime;
    }

}
