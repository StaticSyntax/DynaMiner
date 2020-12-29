package com.staticsyntax.dynaminer.behaviour;

import com.staticsyntax.dynaminer.DynaMiner;
import com.staticsyntax.dynaminer.data.Rock;
import org.osbot.rs07.script.MethodProvider;

import java.util.Arrays;

public class BehaviourProfile {

    private int fatigue = 0;
    private int[] sleepTime = new int[2];
    private int amountPerSwitch, currentAmount, currentTarget;

    public BehaviourProfile(boolean resetFatigue) {
        generateNewProfile(resetFatigue);
    }

    public void generateNewProfile(boolean resetFatigue) {
        if(resetFatigue) fatigue = 0;
        for(int i = 0; i < sleepTime.length; i++) {
            sleepTime[i] = MethodProvider.random(750, 2250);
        }
        Arrays.sort(sleepTime);
        randomiseAmountPerSwitch();
        currentAmount = 0;
        currentTarget = 0;
    }

    public void increaseFatigue() {
        fatigue += MethodProvider.random(1, 5);
    }

    public int[] getSleepTime() {
        int[] sleepTimes = new int[] {sleepTime[0] + fatigue, sleepTime[1] + fatigue};
        return sleepTimes;
    }

    private void randomiseAmountPerSwitch() {
        amountPerSwitch = MethodProvider.random(DynaMiner.getApi().getInventory().getEmptySlots() / Rock.getTargets().length);
    }

    public int getAmountPerSwitch() {
        return amountPerSwitch;
    }

    public void incrementCurrentAmount() {
        currentAmount++;
        if(currentAmount >= amountPerSwitch) {
            currentAmount = 0;
            currentTarget++;
            if(currentTarget >= Rock.getTargets().length) {
                currentTarget = 0;
                randomiseAmountPerSwitch();
            }
        }
    }

    public int getCurrentTarget() {
        return currentTarget;
    }
}
