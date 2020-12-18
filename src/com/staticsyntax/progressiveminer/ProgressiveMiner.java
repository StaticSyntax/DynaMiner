package com.staticsyntax.progressiveminer;

import com.staticsyntax.progressiveminer.tasks.*;
import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import java.awt.*;
import java.util.ArrayList;

@ScriptManifest(logo = "",
        name = "Progressive F2P Miner",
        info = "A progressive mining script for use in free-to-play worlds.",
        version = 0.1,
        author = "StaticSyntax")
public class ProgressiveMiner extends Script {

    private static MethodProvider api;
    private ArrayList<Task> tasks = new ArrayList<>();

    @Override
    public void onStart() {
        api = this;
        initialiseTasks();
    }

    @Override
    public int onLoop() {
        tasks.forEach(task -> task.run());
        return random(750, 1550);
    }

    @Override
    public void onPaint(Graphics2D g) {

    }

    @Override
    public void onMessage(Message m) {

    }

    private void initialiseTasks() {
        tasks.add(new GetPickaxe(this, this));
    }

    public static MethodProvider getApi() {
        return api;
    }
}