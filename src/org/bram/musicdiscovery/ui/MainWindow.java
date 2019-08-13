package org.bram.musicdiscovery.ui;

import org.bram.musicdiscovery.files.data.Artist;
import org.bram.musicdiscovery.files.MusicFinder;
import org.bram.musicdiscovery.ui.listeners.ChooseDirectoryListener;
import org.bram.musicdiscovery.ui.listeners.DiscoveryListener;
import org.bram.musicdiscovery.ui.listeners.StopListeningListener;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MainWindow {
    private JFrame frame;
    private JTextField directoryLocationField;
    private JTextArea logText;
    private JScrollPane scrollPane;
    private JButton discoverAndListenButton;
    private JButton stopButton;
    private MusicFinder musicFinder;

    public MainWindow() {
        musicFinder = new MusicFinder();
    }

    public void showWindow() {
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
        JButton chooseDirectoryButton = new JButton("Choose New...");
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
        scrollPane = new JScrollPane(logText);
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
        // Discover and listen button.
        discoverAndListenButton = new JButton("Discover and Listen");
        discoverAndListenButton.addActionListener(new DiscoveryListener(this));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 3;
        c.weightx = 0.0;
        c.weighty = 0.1;
        c.ipady = 20;
        c.insets = new Insets(0, 10, 10, 10);
        frame.add(discoverAndListenButton, c);
        // Stop button.
        stopButton = new JButton("Stop");
        stopButton.addActionListener(new StopListeningListener(this));
        stopButton.setVisible(false);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 3;
        c.weightx = 0.0;
        c.weighty = 0.1;
        c.ipady = 20;
        c.insets = new Insets(0, 10, 10, 10);
        frame.add(stopButton, c);
        // Show window.
        frame.setVisible(true);
    }

    public void initListen() {
        try {
            logMessage("Finding music...");
            musicFinder.clearMusicData();
            Map<String, Artist> musicData = musicFinder.findMusic(getDirectoryLocation());
            logMessage("Music compiled.");
            discoverAndListenButton.setVisible(false);
            stopButton.setVisible(true);
        } catch (Throwable t) {
            // TODO: Error handling and reset everything.
            logMessage(String.format("Discovery failed: %s", t.getMessage()));
        }
    }

    public void stopListen() {
        discoverAndListenButton.setVisible(true);
        discoverAndListenButton.setEnabled(true);
        stopButton.setVisible(false);
    }

    public String getDirectoryLocation() {
        return directoryLocationField.getText();
    }

    public void setDirectoryLocation(String directoryLocation) {
        directoryLocationField.setText(directoryLocation);
    }

    public void logMessage(String message) {
        logText.append(String.format("%s\n", message));
    }

    public void clearLog() {
        logText.setText("");
    }
}