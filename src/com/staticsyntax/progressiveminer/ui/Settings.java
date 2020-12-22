package com.staticsyntax.progressiveminer.ui;

import com.staticsyntax.progressiveminer.data.Location;
import com.staticsyntax.progressiveminer.data.Rock;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Settings implements ItemListener, ChangeListener {

    private final JDialog mainDialog;
    private final Dimension fillerDimension = new Dimension(0, 25);
    private JComboBox<Location> locationComboBox;
    private JPanel customLocationPanel;
    private JSlider radiusSlider;
    private JLabel radiusValueLabel;

    public Settings() {
        mainDialog = new JDialog();
        mainDialog.setTitle("Static Scripts - Progressive Miner Settings");
        mainDialog.setModal(true);
        mainDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        mainDialog.setSize(500, 500);
        mainDialog.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
        mainDialog.getContentPane().add(mainPanel);

        JPanel powerMinePanel = new JPanel();
        powerMinePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel powerMineLabel = new JLabel("Drop Ores:");
        powerMinePanel.add(powerMineLabel);
        JCheckBox powerMineCheckBox = new JCheckBox();
        powerMinePanel.add(powerMineCheckBox);
        mainPanel.add(powerMinePanel);

        mainPanel.add(new Box.Filler(fillerDimension, fillerDimension, fillerDimension));

        JPanel targetLocationPanel = new JPanel();
        targetLocationPanel.setLayout(new BoxLayout(targetLocationPanel, BoxLayout.PAGE_AXIS));
        JLabel targetLocationLabel = new JLabel("Target Location:");
        targetLocationPanel.add(targetLocationLabel);
        targetLocationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        locationComboBox = new JComboBox<>(Location.getMiningLocations());
        locationComboBox.addItemListener(this);
        targetLocationPanel.add(locationComboBox);
        mainPanel.add(targetLocationPanel);

        mainPanel.add(new Box.Filler(fillerDimension, fillerDimension, fillerDimension));

        customLocationPanel = new JPanel();
        customLocationPanel.setLayout(new BoxLayout(customLocationPanel, BoxLayout.PAGE_AXIS));
        JLabel customLocationLabel = new JLabel("Custom Location:");
        customLocationPanel.add(customLocationLabel);
        customLocationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(customLocationPanel);

        JPanel radiusPanel = new JPanel();
        radiusPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel radiusLabel = new JLabel("Radius:");
        radiusPanel.add(radiusLabel);
        radiusSlider = new JSlider(1, 10);
        radiusSlider.setBackground(Color.CYAN);
        radiusSlider.addChangeListener(this);
        radiusPanel.add(radiusSlider);
        JButton radiusButton = new JButton("Set");
        radiusPanel.add(radiusButton);
        customLocationPanel.add(radiusPanel);

        radiusValueLabel = new JLabel(String.valueOf(radiusSlider.getValue()));
        customLocationPanel.add(radiusValueLabel);
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
        rockSelectionPanel.setLayout(new GridLayout(3, 4));
        for(int i = 0; i < Rock.values().length; i++) {
            JPanel rockPanel = new JPanel();
            rockPanel.setLayout(new BoxLayout(rockPanel, BoxLayout.PAGE_AXIS));
            JLabel rockLabel = new JLabel(Rock.values()[i].name());
            rockPanel.add(rockLabel);
            rockLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JCheckBox rockCheckBox = new JCheckBox();
            rockPanel.add(rockCheckBox);
            rockCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
            rockSelectionPanel.add(rockPanel);
        }
        targetRocksPanel.add(rockSelectionPanel);

        mainPanel.add(new Box.Filler(fillerDimension, fillerDimension, fillerDimension));

        JButton startButton = new JButton("Start");
        startButton.setBackground(Color.CYAN);
        mainPanel.add(startButton);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        setCustomLocationPanelActive(false);
        mainDialog.pack();
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

    @Override
    public void itemStateChanged(ItemEvent e) {
        setCustomLocationPanelActive(locationComboBox.getSelectedItem() == Location.CUSTOM);
    }

    private void setCustomLocationPanelActive(boolean active) {
        for(Component component : customLocationPanel.getComponents()) {
            component.setEnabled(active);
            if(component instanceof JPanel) {
                for(Component childComponent : ((JPanel) component).getComponents()) {
                    childComponent.setEnabled(active);
                }
            }
        }
    }
}