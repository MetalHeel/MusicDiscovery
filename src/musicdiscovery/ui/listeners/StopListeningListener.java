package musicdiscovery.ui.listeners;

import musicdiscovery.ui.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopListeningListener implements ActionListener {
    private MainWindow mainWindow;

    public StopListeningListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.logMessage("Closing down listen.");
        mainWindow.stopListen();
        mainWindow.logMessage("Listening has finished.");
    }
}
