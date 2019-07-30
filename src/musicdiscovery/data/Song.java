package musicdiscovery.data;

public class Song extends DataItem {
    private Album album;
    private String path;
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

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    @Override
    public int compareTo(DataItem dataItem) {
        if (trackNumber < ((Song)dataItem).getTrackNumber()) {
            return -1;
        }
        if (trackNumber > ((Song)dataItem).getTrackNumber()) {
            return 1;
        }
        return 0;
    }
}
