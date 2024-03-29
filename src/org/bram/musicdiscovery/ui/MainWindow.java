package org.bram.musicdiscovery.ui;

import org.bram.musicdiscovery.files.MusicService;
import org.bram.musicdiscovery.ui.listeners.ChooseDirectoryListener;
import org.bram.musicdiscovery.ui.listeners.DiscoveryListener;
import org.bram.musicdiscovery.web.server.MusicServer;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainWindow {
    private JFrame frame;
    private JTextField directoryLocationField;
    private JButton chooseDirectoryButton;
    private JTextArea logText;
    private JButton discoverButton;

    public MainWindow() {
    }

    public void init() {
        // Set up the window.
        frame = new JFrame("Music Discovery");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setSize(500, 500);
        frame.setResizable(false);
        GridBagConstraints c = new GridBagConstraints();
        // Explanation text.
        JTextArea explanationText = new JTextArea("Select or enter your directory below. Then click \"Discover and Listen\" to search directories and start hosting.");
        explanationText.setLineWrap(true);
        explanationText.setOpaque(false);
        explanationText.setFocusable(false);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.weightx = 0.0;
        c.weighty = 0.1;
        c.insets = new Insets(0, 10, 0, 10);
        frame.add(explanationText, c);
        // Reset grid width.
        c.gridwidth = 1;
        // Directory label.
        JLabel directoryLabel = new JLabel("Music Location:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.1;
        c.weighty = 0.1;
        c.insets = new Insets(0, 10, 0, 0);
        frame.add(directoryLabel, c);
        // Directory text.
        directoryLocationField = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.8;
        c.weighty = 0.1;
        c.insets = new Insets(0, 0, 0, 10);
        frame.add(directoryLocationField, c);
        // Choose directory button.
        chooseDirectoryButton = new JButton("Choose New...");
        chooseDirectoryButton.addActionListener(new ChooseDirectoryListener(this));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        c.weightx = 0.1;
        c.weighty = 0.1;
        c.insets = new Insets(0, 0, 0, 10);
        frame.add(chooseDirectoryButton, c);
        // Log text.
        logText = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(logText);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(100, 100));
        logText.setLineWrap(true);
        logText.setBackground(Color.white);
        logText.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        logText.setFocusable(false);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        c.weightx = 0.0;
        c.weighty = 0.8;
        c.insets = new Insets(0, 10, 10, 10);
        frame.add(scrollPane, c);
        // Discover button.
        discoverButton = new JButton("Discover");
        discoverButton.addActionListener(new DiscoveryListener(this));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 3;
        c.weightx = 0.0;
        c.weighty = 0.1;
        c.ipady = 20;
        c.insets = new Insets(0, 10, 10, 10);
        frame.add(discoverButton, c);
        // Show window.
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Init the server
        try {
            MusicServer.init();
        } catch (Exception e) {
            chooseDirectoryButton.setEnabled(false);
            discoverButton.setEnabled(false);
            logMessage(String.format("Could not initialize the server: %s", e.getMessage()));
            logMessage("Cannot discover music.");
        }
    }

    public void initListen() {
        try {
            chooseDirectoryButton.setEnabled(false);
            discoverButton.setEnabled(false);
            logMessage("Finding music...");
            MusicService.findMusic(getDirectoryLocation());
            logMessage(String.format("Music compiled. Currently streaming music from %s.", new File(getDirectoryLocation()).getName()));
        } catch (Throwable t) {
            logMessage(String.format("Discovery failed: %s", t.getMessage()));
        }
        chooseDirectoryButton.setEnabled(true);
        discoverButton.setEnabled(true);
    }

    public String getDirectoryLocation() {
        return directoryLocationField.getText();
    }

    public void setDirectoryLocation(String directoryLocation) {
        directoryLocationField.setText(directoryLocation);
    }

    public void logMessage(String message) {
        logText.append(String.format("%s\n", message));
        frame.repaint();
    }

    public void clearLog() {
        logText.setText("");
    }
}