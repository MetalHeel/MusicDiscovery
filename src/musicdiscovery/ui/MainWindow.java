package musicdiscovery.ui;

import javax.swing.*;

public class MainWindow {
    public MainWindow() {
        JFrame frame = new JFrame("Music Discovery");
        JLabel directoryLabel = new JLabel("Music Location: ");
        directoryLabel.setBounds(10, 10, 100, 30);
        JTextField directoryLocation = new JTextField();
        directoryLocation.setBounds(110, 10, 200, 30);
        JButton chooseDirectory = new JButton("Choose New...");
        chooseDirectory.setBounds(320, 10, 125, 30);
        frame.add(directoryLabel);
        frame.add(directoryLocation);
        frame.add(chooseDirectory);
        frame.setSize(700, 700);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}