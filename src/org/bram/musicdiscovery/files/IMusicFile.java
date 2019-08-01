package org.bram.musicdiscovery.files;

import java.io.File;

public interface IMusicFile {
    void parseFile(File file) throws Exception;

    String getArtist();

    String getAlbum();

    String getSong();

    int getTrack();
}
