package org.bram.musicdiscovery.ui.listeners;

import org.bram.musicdiscovery.ui.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseDirectoryListener implements ActionListener {
    private MainWindow mainWindow;

    public ChooseDirectoryListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser directoryChooser = new JFileChooser("Select Music Location");
        directoryChooser.setCurrentDirectory(new java.io.File("."));
        directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (directoryChooser.showOpenDialog((Component)e.getSource()) == JFileChooser.APPROVE_OPTION) {
            mainWindow.setDirectoryLocation(directoryChooser.getSelectedFile().getPath());
        }
    }
}