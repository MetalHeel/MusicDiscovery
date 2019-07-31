package musicdiscovery.files;

import musicdiscovery.data.Artist;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

public class MusicFinder {
    public static Map<String, Artist> findMusic(String musicDirectory) {
        Map<String, Artist> music = new TreeMap<>();
        File thisDirectory = new File(musicDirectory);
        File[] files = thisDirectory.listFiles((directory, name) -> name.endsWith(".mp3"));
        File[] directories = thisDirectory.listFiles((directory, name) -> new File(directory, name).isDirectory());
        for (int i = 0; i < files.length; i++) {
            File thisFile = files[i];
            // TODO: Process mp3.
        }
        for (int i = 0; i < directories.length; i++) {
            music.putAll(findMusic(directories[i].getPath()));
        }
        return music;
    }
}