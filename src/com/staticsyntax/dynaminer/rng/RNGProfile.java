package com.staticsyntax.dynaminer.rng;

import com.staticsyntax.dynaminer.data.Rock;
import org.osbot.rs07.script.MethodProvider;

import java.util.Arrays;

public class RNGProfile {

    private int fatigue = 0;
    private int[] sleepTime = new int[2];
    private int miningAmount;

    public RNGProfile(boolean resetFatigue) {
        generateNewProfile(resetFatigue);
    }

    public void generateNewProfile(boolean resetFatigue) {
        if(resetFatigue) fatigue = 0;
        for(int i = 0; i < sleepTime.length; i++) {
            sleepTime[i] = MethodProvider.random(750, 2250);
        }
        Arrays.sort(sleepTime);
        miningAmount = MethodProvider.random(28 / Rock.getTargets().length);
    }

    public void increaseFatigue() {
        fatigue += MethodProvider.random(1, 5);
    }

    public int[] getSleepTime() {
        int[] sleepTimes = new int[] {sleepTime[0] + fatigue, sleepTime[1] + fatigue};
        return sleepTimes;
    }

    public int getMiningAmount() {
        return miningAmount;
    }
}
