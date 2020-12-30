package com.staticsyntax.dynaminer.utils;

import com.staticsyntax.dynaminer.DynaMiner;
import org.osbot.rs07.event.WebWalkEvent;
import org.osbot.rs07.event.webwalk.PathPreferenceProfile;
import org.osbot.rs07.script.MethodProvider;

import java.util.Arrays;

public class Utils {

    public static void initWebWalkEvent(WebWalkEvent webWalkEvent) {
        webWalkEvent.setPathPreferenceProfile(getStandardPathPreferenceProfile());
        webWalkEvent.setEnergyThreshold(MethodProvider.random(1, 15));
        if(DynaMiner.getBehaviourProfile().isUsingSimplePaths()) webWalkEvent.useSimplePath();
    }

    private static PathPreferenceProfile getStandardPathPreferenceProfile() {
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
