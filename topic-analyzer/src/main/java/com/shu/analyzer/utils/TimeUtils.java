package com.shu.analyzer.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description：时间工具
 * User:Lichangya
 * Date:2017/4/26.
 */
public class TimeUtils {

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     *计算时间差
     * @param start
     * @param end
     * @return
     */
    public static String timeDistance(Date start,Date end){
        long diff = end.getTime() - start.getTime();
        long days = diff / (1000 * 60 * 60 * 24);
        long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
        long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
        long seconds =(diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60)-minutes*(1000* 60))/1000;
        long milliSeconds =(diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60)-minutes*(1000* 60)-seconds*1000)/1;
        return days+"天"+hours+"小时"+minutes+"分"+seconds+"秒"+milliSeconds+"毫秒";
    }
}
