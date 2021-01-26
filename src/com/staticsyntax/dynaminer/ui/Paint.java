package com.staticsyntax.dynaminer.ui;

import com.staticsyntax.dynaminer.data.Location;
import com.staticsyntax.dynaminer.utils.Sleep;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.MethodProvider;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Paint {

    private MethodProvider api;
    private long startTime;
    private String currentTask;
    private int oresMined;
    private BufferedImage logo;
    private Font monospaced_16;

    public Paint(MethodProvider api) {
        this.api = api;
        startTime = System.currentTimeMillis();
        api.getExperienceTracker().start(Skill.MINING);
        loadResources();
    }

    private void loadResources() {
        try {
            logo = ImageIO.read(new URL("https://i.imgur.com/JXpuvgW.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        monospaced_16 = new Font("Monospaced", Font.BOLD, 16);
    }

    public void draw(Graphics2D g) {
        drawMiningArea(g);
        drawInfo(g);
        drawCursor(g);
    }

    private void drawMiningArea(Graphics2D g) {
        List<Position> positions = Location.MINING.getArea().getPositions();
        g.setColor(new Color(1f, 1f, 1f, 0.1f));
        g.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10.0f, new float[] {10.0f}, 0.0f));
        for(Position pos : positions) {
            Polygon poly = pos.getPolygon(api.getBot());
            g.drawPolygon(poly);
        }
    }

    private void drawInfo(Graphics2D g) {
        g.drawImage(logo, 0, 25, logo.getWidth() / 2, logo.getHeight() / 2, null);
        drawStrings(g);
    }

    private void drawStrings(Graphics2D g) {
        g.setFont(monospaced_16);
        g.setColor(Color.WHITE);
        g.drawString("Runtime: " + Sleep.msToString(System.currentTimeMillis() - startTime), 10, 90);
        g.drawString("Task: " + currentTask, 10, 110);
        g.drawString("Ores Mined: " + oresMined, 10, 130);
        g.drawString("Mining Level: " + api.getSkills().getVirtualLevel(Skill.MINING) + " (+" + api.getExperienceTracker().getGainedLevels(Skill.MINING) + ")", 10, 150);
        g.drawString("Exp Gained: " + api.getExperienceTracker().getGainedXP(Skill.MINING) + " (" + api.getExperienceTracker().getGainedXPPerHour(Skill.MINING) + "/hr)", 10, 170);
    }

    private void drawCursor(Graphics2D g) {
        Point mousePoint = api.getMouse().getPosition();
        g.setStroke(new BasicStroke());
        g.setColor(Color.PINK);
        g.drawLine(mousePoint.x - 5, mousePoint.y + 5, mousePoint.x + 5, mousePoint.y - 5);
        g.setColor(Color.CYAN);
        g.drawLine(mousePoint.x + 5, mousePoint.y + 5, mousePoint.x - 5, mousePoint.y - 5);
    }

    public void setCurrentTask(String currentTask) {
        this.currentTask = currentTask;
    }

    public void incrementOresMined() {
        oresMined++;
    }
}
