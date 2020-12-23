package com.staticsyntax.dynaminer.utils;

import java.util.function.BooleanSupplier;
import java.util.concurrent.TimeUnit;

import org.osbot.rs07.utility.ConditionalSleep;

/**
 * Static utility class with various methods that are related
 * to time / timing.
 *
 * @author The Viking
 *
 */
public class Sleep {
    /**
     * Calculates the time, in ms, from a specific mark
     *
     * @param mark The initial time mark we're calculating from
     * @return The time, in ms, from the provided mark
     */
    public static long timeFromMark(long mark) {
        return System.currentTimeMillis() - mark;
    }

    /**
     * Returns the current time in ms. Essentially just a shorter
     * wrapper for System.currentTimeMillis()
     *
     * @return The current time, in ms
     */
    public static long currentMs() {
        return System.currentTimeMillis();
    }

    /**
     * Converts a time, in ms, to a pretty String in hh:mm:ss:SSS format
     *
     * @param ms The time, in ms, to convert
     * @return A string representing the current time
     */
    public static String msToString(long ms) {
        return String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(ms),
                TimeUnit.MILLISECONDS.toMinutes(ms) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(ms) % TimeUnit.MINUTES.toSeconds(1));
    }

    /**
     * This method waits for a specific condition
     * to be true within a maximum amount of time. Your
     * basic conditional sleep. This method uses the BooleanSupplier functional interface, so it provides lambda support
     *
     * @param condition the condition to wait for
     * @param cycleTime the time, in ms, between condition checks
     * @param timeout the maximum time to wait for the condition to be true
     * @return true if the condition was met within the threshold, or false if the timeout was exceeded
     */
    public static boolean waitCondition(BooleanSupplier condition, int cycleTime, int timeout) {
        return new ConditionalSleep(timeout, cycleTime) {
            @Override
            public boolean condition() {
                try {
                    return condition.getAsBoolean();
                } catch(Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        }.sleep();
    }

    /**
     * This method waits for a specific condition to be true within a maximum amount of time. Your
     * basic conditional sleep. This method uses the BooleanSupplier functional interface, so it provides lambda support
     *
     * @param condition the condition to wait for
     * @param timeout the maximum time to wait for the condition to be true
     * @return true if the condition was met within the threshold, or false if the timeout was exceeded
     */
    public static boolean waitCondition(BooleanSupplier condition, int timeout) {
        return waitCondition(condition, 20, timeout);
    }
}
