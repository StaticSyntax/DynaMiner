package com.staticsyntax.dynaminer.utils;

import org.osbot.rs07.event.webwalk.PathPreferenceProfile;

import java.util.Arrays;

public class Utils {

    public static PathPreferenceProfile getStandardPathPreferenceProfile() {
        PathPreferenceProfile ppp = new PathPreferenceProfile();
        ppp.setAllowTeleports(true);
        return ppp;
    }

    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}
