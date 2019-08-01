package org.bram.musicdiscovery.files.data;

import java.util.Map;
import java.util.TreeMap;

public class Artist implements Comparable<Artist> {
    private String name;
    private Map<String, Album> albums;

    public Artist() {
        super();
        albums = new TreeMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Album> getAlbums() {
        return albums;
    }

    public Album getAlbumByName(String albumName) {
        return albums.get(albumName);
    }

    public void addAlbum(Album album) {
        albums.put(album.getName(), album);
    }

    @Override
    public int compareTo(Artist artist) {
        return name.compareTo(artist.getName());
    }
}
