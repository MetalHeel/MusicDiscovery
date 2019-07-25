package musicdiscovery.ui.listeners;

import musicdiscovery.ui.MainWindow;

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
        mainWindow.logMessage("Checking directory location...");
        String directoryLocation = mainWindow.getDirectoryLocation();
        if (directoryLocation == null || directoryLocation.length() == 0) {
            mainWindow.logMessage("No directory chosen.");
            return;
        }
        File musicDirectory = new File((directoryLocation));
        if (!musicDirectory.exists()) {
            mainWindow.logMessage("Not a valid directory.");
            return;
        }
    }
}
