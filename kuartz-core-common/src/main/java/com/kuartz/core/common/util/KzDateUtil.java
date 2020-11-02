package com.kuartz.core.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class KzDateUtil {

    public static Date now() {
        return new Date();
    }

    public static String MM_YYYY = "MM/yyyy";

    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static int getCurrentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    //region TARIH OLUSTURMA
    public static Date getDayDate(int day) {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.DAY_OF_MONTH, day);
        return instance.getTime();
    }

    public static Date getMonthDate(int month) {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.MONTH, month);
        return instance.getTime();
    }

    public static Date getYearDate(int year) {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR, year);
        instance.set(Calendar.DAY_OF_YEAR, 1);
        return instance.getTime();
    }

    public static Date getDate(int day, int month, int year) {
        Calendar instance = Calendar.getInstance();
        instance.set(day, month, year);
        return instance.getTime();
    }
    //endregion

    //region ZAMAN ISLEMLERI
    public static Date addMinute(Date sonTarih, Integer value) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(sonTarih);
        instance.add(Calendar.MINUTE, value);
        return instance.getTime();
    }

    public static Date addHour(Date sonTarih, Integer value) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(sonTarih);
        instance.add(Calendar.HOUR, value);
        return instance.getTime();
    }
    //endregion

    //region AY ISLEMLERI
    public static Date substractMonth(Date date, int i) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.MONTH, -i);
        return instance.getTime();
    }

    public static Date substractMonthFirstDay(Date date, int i) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.MONTH, -i);
        instance.set(Calendar.DAY_OF_MONTH, instance.getMinimalDaysInFirstWeek());
        return instance.getTime();
    }

    public static Date addMonth(Date date, int i) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.MONTH, i);
        return instance.getTime();
    }

    public static Date addMonthLastDay(Date date, int i) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.MONTH, i);
        instance.set(Calendar.DAY_OF_MONTH, instance.getActualMaximum(Calendar.DAY_OF_MONTH));
        return instance.getTime();
    }

    public static void setMonth(Date date, int i) {
        final Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(Calendar.MONTH, i);
    }

    public static int getDateMonth(Date simdikiDate) {
        final Calendar instance = Calendar.getInstance();
        instance.setTime(simdikiDate);
        return instance.get(Calendar.MONTH);
    }
    //endregion

    public static int getDay(Date date) {
        final Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance.get(Calendar.DAY_OF_MONTH);
    }

    //region FORMATTER
    public static String format(Date tarih, String s) {
        final SimpleDateFormat formatter = new SimpleDateFormat(s);
        return formatter.format(tarih);
    }
    //endregion


}
