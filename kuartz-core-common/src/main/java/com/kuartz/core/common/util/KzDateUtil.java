package com.kuartz.core.common.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Date;

@Slf4j
public class KzDateUtil {

    public static Date suankiTarih() {
        return new Date();
    }

    public static Boolean loeCompare(Date date1, Date date2) {
        Calendar calendar1 = new Calendar.Builder().setInstant(date1).build();
        Calendar calendar2 = new Calendar.Builder().setInstant(date2).build();
        return calendar1.compareTo(calendar2) <= 0;
    }

    public static Boolean goeCompare(Date date1, Date date2) {
        Calendar calendar1 = new Calendar.Builder().setInstant(date1).build();
        Calendar calendar2 = new Calendar.Builder().setInstant(date2).build();
        return calendar1.compareTo(calendar2) >= 0;
    }

    public static Boolean equalCompare(Date date1, Date date2) {
        Calendar calendar1 = new Calendar.Builder().setInstant(date1).build();
        Calendar calendar2 = new Calendar.Builder().setInstant(date2).build();
        return calendar1.compareTo(calendar2) == 0;
    }


}
