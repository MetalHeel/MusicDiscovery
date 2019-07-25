package musicdiscovery.ui;

import musicdiscovery.ui.listeners.ChooseDirectoryListener;
import musicdiscovery.ui.listeners.DiscoveryListener;

import javax.swing.*;
import java.awt.*;

public class MainWindow {
    private JTextField directoryLocationField;
    private JTextArea logText;

    public MainWindow() {
    }

    public void showWindow() {
        // Set up the window.
        JFrame frame = new JFrame("Music Discovery");
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
        logText.setLineWrap(true);
        logText.setBackground(Color.white);
        logText.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        c.weightx = 0.0;
        c.weighty = 0.8;
        c.insets = new Insets(0, 10, 10, 10);
        frame.add(logText, c);
        // Discover and listen button.
        JButton discoverAndListenButton = new JButton("Discover and Listen");
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
        // Show window.
        frame.setVisible(true);
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