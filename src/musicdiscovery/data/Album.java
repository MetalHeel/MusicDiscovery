package musicdiscovery.data;

import java.util.Map;
import java.util.TreeMap;

public class Album extends DataItem {
    private Artist artist;
    private Map<String, Song> songs;
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

    public Map<String, Song> getSongs() {
        return songs;
    }

    public void addSong(Song song) {
        songs.put(song.getId(), song);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(DataItem dataItem) {
        return name.compareTo(((Album)dataItem).getName());
    }
}
