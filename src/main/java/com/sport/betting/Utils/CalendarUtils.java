package com.sport.betting.Utils;

import java.util.Calendar;

public class CalendarUtils {
    public static long startOfWeekOfYear(int year, int week) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        setCalendarStartOfDay(calendar);
        return calendar.getTimeInMillis();
    }
    public static long endOfWeekOfYear(int year, int week) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        setCalendarEndOfDay(calendar);
        return calendar.getTimeInMillis();
    }

    public static long startOfDayOfYear(int year, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_YEAR, day);
        setCalendarStartOfDay(calendar);
        return calendar.getTimeInMillis();
    }

    public static long endOfDayOfYear(int year, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_YEAR, day);
        setCalendarEndOfDay(calendar);
        return calendar.getTimeInMillis();
    }

    public static long startOfMonthOfYear(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        setCalendarStartOfDay(calendar);
        return calendar.getTimeInMillis();
    }

    public static long endOfMonthOfYear(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        setCalendarEndOfDay(calendar);
        return calendar.getTimeInMillis();
    }

    private static void setCalendarStartOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    private static void setCalendarEndOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
    }

}
