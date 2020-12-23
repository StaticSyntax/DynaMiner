package com.staticsyntax.dynaminer.utils;

public class Time {

    public static final String formatTime(final long ms){
        long s = ms / 1000, m = s / 60, h = m / 60;
        s %= 60; m %= 60; h %= 24;
        return String.format("%02d:%02d:%02d", h, m, s);
    }

    public static final String getFormattedRunningTime() {
        //return formatTime(System.currentTimeMillis() - getStartTime());
        return formatTime(System.currentTimeMillis());
    }
}
