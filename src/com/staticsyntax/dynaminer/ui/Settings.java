package com.staticsyntax.dynaminer.ui;

import com.staticsyntax.dynaminer.DynaMiner;
import com.staticsyntax.dynaminer.data.Location;
import com.staticsyntax.dynaminer.data.Rock;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class Settings implements ChangeListener {

    private static boolean powerMining = false;

    private final JDialog mainDialog;
    private final Dimension fillerDimension = new Dimension(0, 25);
    private final Font monoFont_14 = new Font("Monospaced", Font.BOLD, 14);
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

        JPanel miningLocationPanel = new JPanel();
        miningLocationPanel.setLayout(new BoxLayout(miningLocationPanel, BoxLayout.PAGE_AXIS));
        JLabel miningLocationLabel = new JLabel("Stand in the center of your target mining location and click set once you have selected the desired radius around your player.");
        miningLocationLabel.setFont(monoFont_14);
        miningLocationPanel.add(miningLocationLabel);
        miningLocationPanel.add(new Box.Filler(fillerDimension, fillerDimension, fillerDimension));
        miningLocationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(miningLocationPanel);

        JPanel radiusPanel = new JPanel();
        radiusPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel radiusLabel = new JLabel("Radius:");
        radiusPanel.add(radiusLabel);
        radiusSlider = new JSlider(JSlider.HORIZONTAL, 1, 25, 5);
        radiusSlider.setBackground(Color.CYAN);
        radiusSlider.addChangeListener(this);
        radiusPanel.add(radiusSlider);
        JButton radiusButton = new JButton("Set");
        radiusButton.setBackground(Color.CYAN);
        radiusButton.addActionListener(e -> {
            Location.setMiningArea();
        });
        radiusPanel.add(radiusButton);
        miningLocationPanel.add(radiusPanel);

        radiusValueLabel = new JLabel(String.valueOf(radiusSlider.getValue()));
        miningLocationPanel.add(radiusValueLabel);
        radiusValueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(new Box.Filler(fillerDimension, fillerDimension, fillerDimension));

        JPanel targetRocksPanel = new JPanel();
        targetRocksPanel.setLayout(new BoxLayout(targetRocksPanel, BoxLayout.PAGE_AXIS));
        JLabel targetRocksLabel = new JLabel("Target Rocks:");
        targetRocksPanel.add(targetRocksLabel);
        targetRocksLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        targetRocksPanel.add(new Box.Filler(fillerDimension, fillerDimension, fillerDimension));
        mainPanel.add(targetRocksPanel);

        JPanel rockSelectionPanel = new JPanel();
        rockSelectionPanel.setLayout(new GridLayout(2, 6, 0, 5));
        for(int i = 0; i < Rock.values().length; i++) {
            JPanel rockPanel = new JPanel();
            rockPanel.setLayout(new BoxLayout(rockPanel, BoxLayout.PAGE_AXIS));
            JLabel rockLabel = new JLabel(Rock.values()[i].name());
            rockPanel.add(rockLabel);
            rockLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JCheckBox rockCheckBox = new JCheckBox();
            int finalI = i;
            rockCheckBox.addActionListener(e -> Rock.values()[finalI].setTarget(rockCheckBox.isSelected()));
            rockPanel.add(rockCheckBox);
            rockCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
            rockSelectionPanel.add(rockPanel);
        }
        targetRocksPanel.add(rockSelectionPanel);

        mainPanel.add(new Box.Filler(fillerDimension, fillerDimension, fillerDimension));

        JPanel powerMinePanel = new JPanel();
        powerMinePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel powerMineLabel = new JLabel("Drop Ores:");
        powerMinePanel.add(powerMineLabel);
        JCheckBox powerMineCheckBox = new JCheckBox();
        powerMineCheckBox.addActionListener(e -> powerMining = powerMineCheckBox.isSelected());
        powerMinePanel.add(powerMineCheckBox);
        mainPanel.add(powerMinePanel);

        mainPanel.add(new Box.Filler(fillerDimension, fillerDimension, fillerDimension));

        JButton startButton = new JButton("Start");
        startButton.setBackground(Color.CYAN);
        startButton.addActionListener(e -> {
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

    public int getMiningRadius() {
        return radiusSlider.getValue();
    }
}
