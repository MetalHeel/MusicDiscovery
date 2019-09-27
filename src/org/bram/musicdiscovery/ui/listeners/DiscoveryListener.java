package org.bram.musicdiscovery.ui.listeners;

import org.bram.musicdiscovery.ui.MainWindow;
import org.bram.musicdiscovery.utils.StringUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class DiscoveryListener implements ActionListener {
    private MainWindow mainWindow;

    public DiscoveryListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.clearLog();
        ((JButton)e.getSource()).setEnabled(false);
        Thread t = new Thread(() -> {
            mainWindow.logMessage("Checking directory location...");
            String directoryLocation = mainWindow.getDirectoryLocation();
            if (StringUtils.isBlank(directoryLocation)) {
                mainWindow.logMessage("No directory chosen.");
                return;
            }
            File musicDirectory = new File((directoryLocation));
            if (!musicDirectory.isDirectory()) {
                mainWindow.logMessage("Location chosen is not a directory.");
                return;
            }
            if (!musicDirectory.exists()) {
                mainWindow.logMessage("Not a valid directory.");
                return;
            }
            mainWindow.logMessage("Valid directory found.");
            mainWindow.initListen();
            ((JButton)e.getSource()).setEnabled(true);
        });
        t.start();
    }
}
