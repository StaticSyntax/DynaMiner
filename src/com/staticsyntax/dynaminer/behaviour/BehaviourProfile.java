package com.staticsyntax.dynaminer.behaviour;

import com.staticsyntax.dynaminer.DynaMiner;
import com.staticsyntax.dynaminer.data.Rock;
import org.osbot.rs07.script.MethodProvider;

import java.util.Arrays;

public class BehaviourProfile {

    private int fatigue;
    private int[] sleepTime = new int[2];
    private int amountPerSwitch, currentAmount, currentTarget;
    private boolean usingSimplePaths;

    public BehaviourProfile() {
        fatigue = 0;
        generateNewProfile();
    }

    public void generateNewProfile() {
        for(int i = 0; i < sleepTime.length; i++) {
            sleepTime[i] = MethodProvider.random(750, 1250);
        }
        Arrays.sort(sleepTime);
        randomiseAmountPerSwitch();
        currentAmount = 0;
        currentTarget = 0;
        usingSimplePaths = MethodProvider.random(100) >= 50;
    }

    public void increaseFatigue() {
        fatigue += MethodProvider.random(1, 10);
    }

    public int getSleepTime() {
        return MethodProvider.random(sleepTime[0] + fatigue, sleepTime[1] + fatigue);
    }

    private void randomiseAmountPerSwitch() {
        int amount = DynaMiner.getApi().getInventory().getEmptySlots() / Rock.getTargets().length;
        if(amount <= 0) amount = 1;
        amountPerSwitch = MethodProvider.random(amount);
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

    public boolean isUsingSimplePaths() {
        return usingSimplePaths;
    }
}
