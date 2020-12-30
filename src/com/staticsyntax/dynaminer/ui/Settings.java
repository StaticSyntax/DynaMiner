package com.staticsyntax.dynaminer.ui;

import com.staticsyntax.dynaminer.DynaMiner;
import com.staticsyntax.dynaminer.data.Location;
import com.staticsyntax.dynaminer.data.Rock;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Settings implements ChangeListener {

    private boolean powerMining, idlingRandomly, usingDepositBoxes, worldHopping;

    private final JDialog mainDialog;
    private final Dimension fillerDimension = new Dimension(0, 25);
    private final Font monoFont_12 = new Font("Monospaced", Font.BOLD, 12);
    private final JLabel radiusValueLabel;
    private final JSlider radiusSlider;

    public Settings() {
        mainDialog = new JDialog();
        mainDialog.setTitle("DynaMiner Settings");
        mainDialog.setModal(true);
        mainDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
        mainDialog.getContentPane().add(mainPanel);

        try {
            JLabel logoLabel = new JLabel(new ImageIcon(new URL("https://i.imgur.com/bc6zxh8.png")));
            mainPanel.add(logoLabel);
            logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        mainPanel.add(new Box.Filler(fillerDimension, fillerDimension, fillerDimension));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
        JLabel infoLabel = new JLabel("Position your player in the center of your mining location and select the radius of the area you would like to mine in.");
        infoLabel.setFont(monoFont_12);
        infoPanel.add(infoLabel);
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(infoPanel);

        mainPanel.add(new Box.Filler(fillerDimension, fillerDimension, fillerDimension));

        JPanel radiusPanel = new JPanel();
        radiusPanel.setLayout(new BoxLayout(radiusPanel, BoxLayout.PAGE_AXIS));
        JLabel radiusLabel = new JLabel("Radius:");
        radiusPanel.add(radiusLabel);
        radiusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        radiusSlider = new JSlider(1, 25, 5);
        radiusSlider.setBackground(Color.CYAN);
        radiusSlider.addChangeListener(this);
        radiusPanel.add(radiusSlider);
        radiusSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
        radiusValueLabel = new JLabel(String.valueOf(radiusSlider.getValue()));
        radiusPanel.add(radiusValueLabel);
        radiusValueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(radiusPanel);

        mainPanel.add(new Box.Filler(fillerDimension, fillerDimension, fillerDimension));

        JPanel targetRocksPanel = new JPanel();
        targetRocksPanel.setLayout(new BoxLayout(targetRocksPanel, BoxLayout.PAGE_AXIS));
        JLabel targetRocksLabel = new JLabel("Target Rocks:");
        targetRocksPanel.add(targetRocksLabel);
        targetRocksLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        targetRocksPanel.add(new Box.Filler(fillerDimension, fillerDimension, fillerDimension));
        mainPanel.add(targetRocksPanel);

        JPanel rockSelectionPanel = new JPanel();
        rockSelectionPanel.setLayout(new GridLayout(4, 4, 0, 5));
        for(int i = 0; i < Rock.values().length; i++) {
            JPanel rockPanel = new JPanel();
            rockPanel.setLayout(new BoxLayout(rockPanel, BoxLayout.PAGE_AXIS));
            JLabel rockLabel = new JLabel(Rock.values()[i].name());
            rockPanel.add(rockLabel);
            rockLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JCheckBox rockCheckBox = new JCheckBox();
            rockCheckBox.setBackground(Color.CYAN);
            rockCheckBox.setForeground(Color.BLACK);
            int finalI = i;
            rockCheckBox.addActionListener(e -> Rock.values()[finalI].setTarget(rockCheckBox.isSelected()));
            rockPanel.add(rockCheckBox);
            rockCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
            rockSelectionPanel.add(rockPanel);
        }
        targetRocksPanel.add(rockSelectionPanel);

        mainPanel.add(new Box.Filler(fillerDimension, fillerDimension, fillerDimension));

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel powerMinePanel = new JPanel();
        powerMinePanel.setLayout(new BoxLayout(powerMinePanel, BoxLayout.PAGE_AXIS));
        powerMinePanel.setBorder(new EmptyBorder(5, 15, 5, 15));
        JLabel powerMineLabel = new JLabel("Power Mining");
        powerMinePanel.add(powerMineLabel);
        powerMineLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JCheckBox powerMineCheckBox = new JCheckBox();
        powerMineCheckBox.setBackground(Color.CYAN);
        powerMineCheckBox.setForeground(Color.BLACK);
        powerMineCheckBox.addActionListener(e -> powerMining = powerMineCheckBox.isSelected());
        powerMinePanel.add(powerMineCheckBox);
        powerMineCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionsPanel.add(powerMinePanel);

        JPanel depositBoxPanel = new JPanel();
        depositBoxPanel.setLayout(new BoxLayout(depositBoxPanel, BoxLayout.PAGE_AXIS));
        depositBoxPanel.setBorder(new EmptyBorder(5, 15, 5, 15));
        JLabel depositBoxLabel = new JLabel("Use Deposit Boxes");
        depositBoxPanel.add(depositBoxLabel);
        depositBoxLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JCheckBox depositBoxCheckBox = new JCheckBox();
        depositBoxCheckBox.setBackground(Color.CYAN);
        depositBoxCheckBox.setForeground(Color.BLACK);
        depositBoxCheckBox.addActionListener(e -> usingDepositBoxes = depositBoxCheckBox.isSelected());
        depositBoxPanel.add(depositBoxCheckBox);
        depositBoxCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionsPanel.add(depositBoxPanel);

        JPanel hopWorldsPanel = new JPanel();
        hopWorldsPanel.setLayout(new BoxLayout(hopWorldsPanel, BoxLayout.PAGE_AXIS));
        hopWorldsPanel.setBorder(new EmptyBorder(5, 15, 5, 15));
        JLabel hopWorldsLabel = new JLabel("Hop Worlds");
        hopWorldsPanel.add(hopWorldsLabel);
        hopWorldsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JCheckBox hopWorldsCheckBox = new JCheckBox();
        hopWorldsCheckBox.setBackground(Color.CYAN);
        hopWorldsCheckBox.setForeground(Color.BLACK);
        hopWorldsCheckBox.addActionListener(e -> worldHopping = hopWorldsCheckBox.isSelected());
        hopWorldsPanel.add(hopWorldsCheckBox);
        hopWorldsCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionsPanel.add(hopWorldsPanel);

        JPanel idlePanel = new JPanel();
        idlePanel.setLayout(new BoxLayout(idlePanel, BoxLayout.PAGE_AXIS));
        idlePanel.setBorder(new EmptyBorder(5, 15, 5, 15));
        JLabel idleLabel = new JLabel("Idle Randomly");
        idlePanel.add(idleLabel);
        idleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JCheckBox idleCheckBox = new JCheckBox();
        idleCheckBox.setBackground(Color.CYAN);
        idleCheckBox.setForeground(Color.BLACK);
        idleCheckBox.addActionListener(e -> idlingRandomly = idleCheckBox.isSelected());
        idlePanel.add(idleCheckBox);
        idleCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionsPanel.add(idlePanel);
        mainPanel.add(optionsPanel);

        mainPanel.add(new Box.Filler(fillerDimension, fillerDimension, fillerDimension));

        JButton startButton = new JButton("Start");
        startButton.setBackground(Color.CYAN);
        startButton.addActionListener(e -> {
            Location.setMiningArea();
            DynaMiner.initBehaviourProfile();
            DynaMiner.setRunning(true);
            close();
        });
        mainPanel.add(startButton);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainDialog.pack();
        mainDialog.setLocationRelativeTo(null);
    }

    public void open() {
        mainDialog.setVisible(true);
    }

    public void close() {
        mainDialog.setVisible(false);
        mainDialog.dispose();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        radiusValueLabel.setText(String.valueOf(radiusSlider.getValue()));
    }

    public boolean isPowerMining() {
        return powerMining;
    }

    public boolean isIdlingRandomly() {
        return idlingRandomly;
    }

    public boolean isUsingDepositBoxes() {
        return usingDepositBoxes;
    }

    public boolean isWorldHopping() {
        return worldHopping;
    }

    public int getRadius() {
        return radiusSlider.getValue();
    }
}
