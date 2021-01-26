package com.staticsyntax.dynaminer.tasks;

import com.staticsyntax.dynaminer.DynaMiner;
import com.staticsyntax.dynaminer.utils.Sleep;
import org.osbot.rs07.script.MethodProvider;

public class Idle extends Task {

    public Idle(DynaMiner script, String name) {
        super(script, name);
    }

    @Override
    public boolean canProcess() {
        return script.getMiningSettings().isIdlingRandomly() && MethodProvider.random(100) <= 1;
    }

    @Override
    public void process() {
        api.getMouse().moveOutsideScreen();
        Sleep.waitCondition(() -> api.myPlayer().isUnderAttack(), script.getBehaviourProfile().getSleepTime() * MethodProvider.random(6, 12));
    }
}
