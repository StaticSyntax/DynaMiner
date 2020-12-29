package com.staticsyntax.dynaminer.tasks;

import com.staticsyntax.dynaminer.DynaMiner;
import com.staticsyntax.dynaminer.utils.Sleep;
import org.osbot.rs07.script.MethodProvider;

public class Idle extends Task {

    public Idle(MethodProvider api) {
        super(api);
    }

    @Override
    public boolean canProcess() {
        return DynaMiner.getMiningSettings().isIdlingRandomly() && MethodProvider.random(100) <= 1;
    }

    @Override
    public void process() {
        api.getMouse().moveOutsideScreen();
        Sleep.waitCondition(() -> api.myPlayer().isUnderAttack(), DynaMiner.getBehaviourProfile().getSleepTime() * MethodProvider.random(6, 12));
    }
}
