package com.staticsyntax.dynaminer.tasks;

import com.staticsyntax.dynaminer.DynaMiner;
import org.osbot.rs07.script.MethodProvider;

public abstract class Task {

    protected DynaMiner script;
    protected MethodProvider api;
    protected String name;

    public Task(DynaMiner script, String name) {
        this.script = script;
        api = script.getApi();
        this.name = name;
    }

    public abstract boolean canProcess();

    public abstract void process();

    public void run() {
        if(canProcess()) {
            script.getPaint().setCurrentTask(name);
            process();
        }
    }
}
