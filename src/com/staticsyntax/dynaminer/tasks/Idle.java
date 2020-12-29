package com.staticsyntax.dynaminer.tasks;

import com.staticsyntax.dynaminer.DynaMiner;
import org.osbot.rs07.script.MethodProvider;

public class Idle extends Task {

    public Idle(MethodProvider api) {
        super(api);
    }

    @Override
    public boolean canProcess() {
        return DynaMiner.getMiningSettings().isIdlingRandomly() && MethodProvider.random(100) <= MethodProvider.random(0, 4);
    }

    @Override
    public void process() {
        api.getMouse().moveOutsideScreen();
        try {
            api.sleep(DynaMiner.getBehaviourProfile().getSleepTime() * MethodProvider.random(4, 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
