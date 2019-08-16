package org.bram.musicdiscovery.files;

import com.sun.source.tree.Tree;
import org.bram.musicdiscovery.files.data.Album;
import org.bram.musicdiscovery.files.data.Artist;
import org.bram.musicdiscovery.files.data.Song;

import java.io.File;
import java.util.*;

public class MusicService {
    private static Map<String, Artist> music;

    public static void findMusic(String musicDirectory) throws Exception {
        music = findMusicHelper(musicDirectory);
    }

    private static Map<String, Artist> findMusicHelper(String musicDirectory) throws Exception {
        Map<String, Artist> newMusic = new TreeMap<>();
        File thisDirectory = new File(musicDirectory);
        // TODO: Other music file types.
        File[] files = thisDirectory.listFiles((directory, name) -> name.endsWith(".mp3"));
        File[] directories = thisDirectory.listFiles((directory, name) -> new File(directory, name).isDirectory());
        if (files != null) {
            for (File file : files) {
                IMusicFile musicFile = new Mp3MusicFile();
                musicFile.parseFile(file);
                // Artist
                Artist artist = newMusic.get(musicFile.getArtist());
                if (artist == null) {
                    artist = new Artist();
                    artist.setName(musicFile.getArtist());
                    newMusic.put(artist.getName(), artist);
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
                newMusic.putAll(findMusicHelper(directory.getPath()));
            }
        }
        return newMusic;
    }

    public static Set<String> getAllArtists() {
        Set<String> artists = new TreeSet<>();
        artists.addAll(music.keySet());
        return artists;
    }
}