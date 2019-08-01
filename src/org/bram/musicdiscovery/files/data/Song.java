package org.bram.musicdiscovery.files.data;

public class Song implements Comparable<Song> {
    private Album album;
    private String path;
    private String name;
    private int trackNumber;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    @Override
    public int compareTo(Song song) {
        if (trackNumber < song.getTrackNumber()) {
            return -1;
        }
        if (trackNumber > song.getTrackNumber()) {
            return 1;
        }
        return 0;
    }
}
