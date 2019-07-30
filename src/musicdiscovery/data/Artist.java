package musicdiscovery.data;

import java.util.Map;
import java.util.TreeMap;

public class Artist extends DataItem {
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

    public void addAlbum(Album album) {
        albums.put(album.getId(), album);
    }

    @Override
    public int compareTo(DataItem dataItem) {
        return name.compareTo(((Artist)dataItem).getName());
    }
}
