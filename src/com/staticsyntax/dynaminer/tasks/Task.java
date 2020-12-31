package com.staticsyntax.dynaminer.tasks;

import com.staticsyntax.dynaminer.DynaMiner;
import org.osbot.rs07.script.MethodProvider;

public abstract class Task {

    protected DynaMiner script;
    protected MethodProvider api;

    public Task(DynaMiner script) {
        this.script = script;
        api = script.getApi();
    }

    public abstract boolean canProcess();

    public abstract void process();

    public void run() {
        if(canProcess()) {
            script.getPaint().setCurrentTask(this.getClass().getSimpleName());
            process();
        }
    }
}
