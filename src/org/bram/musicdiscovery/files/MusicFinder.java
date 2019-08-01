package org.bram.musicdiscovery.files;

import org.bram.musicdiscovery.files.data.Album;
import org.bram.musicdiscovery.files.data.Artist;
import org.bram.musicdiscovery.files.data.Song;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

public class MusicFinder {
    private Map<String, Artist> music;

    public MusicFinder() {
        music = new TreeMap<>();
    }

    public Map<String, Artist> findMusic(String musicDirectory) throws Exception {
        // TODO: Warn that this will clear all existing music, or maybe some sort of cache?
        music.clear();
        File thisDirectory = new File(musicDirectory);
        // TODO: Other music file types.
        File[] files = thisDirectory.listFiles((directory, name) -> name.endsWith(".mp3"));
        File[] directories = thisDirectory.listFiles((directory, name) -> new File(directory, name).isDirectory());
        if (files != null) {
            for (File file : files) {
                IMusicFile musicFile = new Mp3MusicFile();
                musicFile.parseFile(file);
                // Artist
                Artist artist = music.get(musicFile.getArtist());
                if (artist == null) {
                    artist = new Artist();
                    artist.setName(musicFile.getArtist());
                    music.put(artist.getName(), artist);
                }
                // Album
                Album album = artist.getAlbumByName(musicFile.getAlbum());
                if (album == null) {
                    album = new Album();
                    album.setArtist(artist);
                    album.setName(musicFile.getAlbum());
                    artist.addAlbum(album);
                }
                // Song
                Song song = new Song();
                song.setAlbum(album);
                song.setPath(file.getPath());
                song.setName(musicFile.getSong());
                song.setTrackNumber(musicFile.getTrack());
                album.addSong(song);
            }
        }
        if (directories != null) {
            for (File directory : directories) {
                music.putAll(findMusic(directory.getPath()));
            }
        }
        return music;
    }
}