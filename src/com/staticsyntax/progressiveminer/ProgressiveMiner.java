package com.staticsyntax.progressiveminer;

import com.staticsyntax.progressiveminer.tasks.*;
import com.staticsyntax.progressiveminer.ui.Settings;
import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

@ScriptManifest(logo = "",
        name = "Progressive F2P Miner",
        info = "A progressive mining script for use in free-to-play worlds.",
        version = 0.1,
        author = "StaticSyntax")
public class ProgressiveMiner extends Script {

    private static boolean running = false;
    private static MethodProvider api;
    private Settings settings = new Settings();
    private ArrayList<Task> tasks = new ArrayList<>();

    @Override
    public void onStart() {
        api = this;
        initSettings();
        initTasks();
    }

    @Override
    public int onLoop() {
        if(running) {
            tasks.forEach(task -> task.run());
        }
        return random(750, 1550);
    }

    @Override
    public void onPaint(Graphics2D g) {

    }

    @Override
    public void onMessage(Message m) {

    }

    private void initSettings() {
        try {
            SwingUtilities.invokeAndWait(() -> settings.open());
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
            stop(false);
            return;
        }

        if (!running) {
            stop(false);
        }
    }

    private void initTasks() {
        tasks.add(new GetPickaxe(this, this));
        tasks.add(new BankJunk(this));
    }

    public static void setRunning(boolean running) {
        ProgressiveMiner.running = running;
    }

    public static MethodProvider getApi() {
        return api;
    }
}
