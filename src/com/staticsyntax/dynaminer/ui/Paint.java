package com.staticsyntax.dynaminer.ui;

import com.staticsyntax.dynaminer.utils.Sleep;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.MethodProvider;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Paint {

    private MethodProvider api;
    private long startTime;
    private String currentTask;
    private int oresMined;
    private BufferedImage logo, cursor;
    private Font monospaced_16;

    public Paint(MethodProvider api) {
        this.api = api;
        startTime = System.currentTimeMillis();
        api.getExperienceTracker().start(Skill.MINING);
        loadResources();
    }

    private void loadResources() {
        try {
            logo = ImageIO.read(new URL("https://i.imgur.com/bc6zxh8.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            cursor = ImageIO.read(new URL("https://i.imgur.com/no0bPZ9.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        monospaced_16 = new Font("Monospaced", Font.BOLD, 16);
    }

    public void draw(Graphics2D g) {
        g.drawImage(logo, 5, 25, logo.getWidth() / 2, logo.getHeight() / 2, null);
        drawStrings(g);
        drawCursor(g);
    }

    private void drawStrings(Graphics2D g) {
        g.setFont(monospaced_16);
        g.setColor(Color.WHITE);
        g.drawString("Running Time: " + Sleep.msToString(System.currentTimeMillis() - startTime), 10, 110);
        g.drawString("Current Task: " + currentTask, 10, 130);
        g.drawString("Ores Mined: " + oresMined, 10, 150);
        g.drawString("Mining Level: " + api.getSkills().getVirtualLevel(Skill.MINING) + " (+" + api.getExperienceTracker().getGainedLevels(Skill.MINING) + ")", 10, 170);
        g.drawString("Exp Gained: " + api.getExperienceTracker().getGainedXP(Skill.MINING) + " (" + api.getExperienceTracker().getGainedXPPerHour(Skill.MINING) + "/hr)", 10, 190);
    }

    private void drawCursor(Graphics2D g) {
        int width = cursor.getWidth() / 6;
        int height = cursor.getHeight() / 6;
        g.drawImage(cursor, api.getMouse().getPosition().x - width / 2, api.getMouse().getPosition().y - height / 2, width, height, null);
    }

    public void setCurrentTask(String currentTask) {
        this.currentTask = currentTask;
    }

    public void incrementOresMined() {
        oresMined++;
    }
}
