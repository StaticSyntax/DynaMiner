package com.staticsyntax.progressiveminer.tasks;

import org.osbot.rs07.script.MethodProvider;

public abstract class Task {

    protected MethodProvider api;

    public Task(MethodProvider api) {
        this.api = api;
    }

    public abstract boolean canProcess();

    public abstract void process();

    public void run() {
        if (canProcess())
            process();
    }
}
