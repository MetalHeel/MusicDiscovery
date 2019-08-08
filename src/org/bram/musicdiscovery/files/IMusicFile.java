package org.bram.musicdiscovery.files;

import java.io.File;

public interface IMusicFile {
    String UNKNOWN = "Unknown";

    void parseFile(File file) throws Exception;

    String getArtist();

    String getAlbum();

    String getSong();

    int getTrack();
}
