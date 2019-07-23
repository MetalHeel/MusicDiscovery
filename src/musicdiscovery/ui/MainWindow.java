package musicdiscovery.ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow {
    public MainWindow() {
        // Set up the window.
        JFrame frame = new JFrame("Music Discovery");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setSize(500, 500);
        GridBagConstraints c = new GridBagConstraints();
        // Explanation text.
        JTextArea explanation = new JTextArea("Select or enter your directory below. Then click \"Discover and Listen\" to search directories and start hosting.");
        explanation.setLineWrap(true);
        explanation.setOpaque(false);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.weightx = 0.0;
        c.weighty = 0.1;
        c.insets = new Insets(0, 10, 0, 10);
        frame.add(explanation, c);
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
        JTextField directoryLocation = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.8;
        c.weighty = 0.1;
        c.insets = new Insets(0, 0, 0, 10);
        frame.add(directoryLocation, c);
        // Choose directory button.
        JButton chooseDirectory = new JButton("Choose New...");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        c.weightx = 0.1;
        c.weighty = 0.1;
        c.insets = new Insets(0, 0, 0, 10);
        frame.add(chooseDirectory, c);
        // Log text.
        JTextArea log = new JTextArea();
        log.setLineWrap(true);
        log.setBackground(Color.white);
        log.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        c.weightx = 0.0;
        c.weighty = 0.8;
        c.insets = new Insets(0, 10, 10, 10);
        frame.add(log, c);
        // Discover and listen button.
        JButton discoverAndListen = new JButton("Discover and Listen");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 3;
        c.weightx = 0.0;
        c.weighty = 0.1;
        c.ipady = 20;
        c.insets = new Insets(0, 10, 10, 10);
        frame.add(discoverAndListen, c);
        // Show window.
        frame.setVisible(true);
    }
}