package org.bram.musicdiscovery.files.data;

import java.util.Map;
import java.util.TreeMap;

public class Album implements Comparable<Album> {
    private Artist artist;
    private Map<Integer, Song> songs;
    private String name;

    public Album() {
        super();
        songs = new TreeMap<>();
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Map<Integer, Song> getSongs() {
        return songs;
    }

    public void addSong(Song song) {
        songs.put(Integer.valueOf(song.getTrackNumber()), song);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Album album) {
        return name.compareTo(album.getName());
    }
}
