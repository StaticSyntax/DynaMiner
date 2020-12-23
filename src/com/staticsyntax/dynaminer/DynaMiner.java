package com.staticsyntax.dynaminer;

import com.staticsyntax.dynaminer.tasks.*;
import com.staticsyntax.dynaminer.ui.Settings;
import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

@ScriptManifest(logo = "",
        name = "DynaMiner",
        info = "",
        version = 0.1,
        author = "StaticSyntax")
public class DynaMiner extends Script {

    private static boolean running = false;
    private static MethodProvider api;
    private static Settings settings = new Settings();
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
        tasks.add(new UpgradePickaxe(this));
        tasks.add(new WieldPickaxe(this));
    }

    public static void setRunning(boolean running) {
        DynaMiner.running = running;
    }

    public static MethodProvider getApi() {
        return api;
    }

    public static Settings getMiningSettings() {
        return settings;
    }
}
