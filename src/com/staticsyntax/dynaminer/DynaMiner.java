package com.staticsyntax.dynaminer;

import com.staticsyntax.dynaminer.behaviour.BehaviourProfile;
import com.staticsyntax.dynaminer.data.Rock;
import com.staticsyntax.dynaminer.tasks.*;
import com.staticsyntax.dynaminer.ui.Paint;
import com.staticsyntax.dynaminer.ui.Settings;
import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

@ScriptManifest(logo = "https://i.imgur.com/W3RQhXk.png",
        name = "DynaMiner",
        info = "Static Scripts",
        version = 1.0,
        author = "StaticSyntax")
public class DynaMiner extends Script {

    private boolean running = false;
    private ArrayList<Task> tasks = new ArrayList<>();

    private MethodProvider api;
    private BehaviourProfile behaviourProfile;
    private Settings settings;
    private Paint paint;

    @Override
    public void onStart() {
        api = this;
        initSettings();
        initTasks();
    }

    @Override
    public int onLoop() {
        if(running) {
            for(Task task : tasks) {
                task.run();
            }
        }
        return behaviourProfile.getSleepTime();
    }

    @Override
    public void onPaint(Graphics2D g) {
        if(running) {
            paint.draw(g);
        }
    }

    @Override
    public void onMessage(Message m) {
        if(m.getMessage().contains("You manage to mine some")) {
            behaviourProfile.incrementCurrentAmount();
            paint.incrementOresMined();
        }
    }

    private void initSettings() {
        Rock.deselectAllTargets();
        settings = new Settings(this);
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
        tasks.add(new GetPickaxe(this));
        tasks.add(new BankJunk(this));
        tasks.add(new WieldPickaxe(this));
        tasks.add(new OrganiseInventory(this));
        tasks.add(new PathToMiningLocation(this));
        tasks.add(new MineRocks(this));
        tasks.add(new HopWorld(this));
        tasks.add(new Idle(this));
        tasks.add(new BankOres(this));
        tasks.add(new DropOres(this));
        tasks.add(new UpgradePickaxe(this));
    }

    public void setRunning(boolean running) {
        paint = new Paint(this);
        this.running = running;
    }

    public MethodProvider getApi() {
        return api;
    }

    public void initBehaviourProfile() {
        behaviourProfile = new BehaviourProfile(this);
    }

    public BehaviourProfile getBehaviourProfile() {
        return behaviourProfile;
    }

    public Settings getMiningSettings() {
        return settings;
    }

    public Paint getPaint() {
        return paint;
    }
}
