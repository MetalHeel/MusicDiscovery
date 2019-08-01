package org.bram.musicdiscovery.files;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.ID3v1Tag;
import org.jaudiotagger.tag.id3.ID3v24Frames;

import java.io.File;

public class Mp3MusicFile implements IMusicFile {
    private String artist = null;
    private String album = null;
    private String song = null;
    private int track = -1;

    @Override
    public void parseFile(File file) throws Exception {
        MP3File mp3File = (MP3File) AudioFileIO.read(file);
        if (mp3File.hasID3v1Tag()) {
            ID3v1Tag tag = mp3File.getID3v1Tag();
            artist = tag.getFirstArtist();
            album = tag.getFirstAlbum();
            song = tag.getFirstTitle();
            track = Integer.parseInt(tag.getFirstTrack());
            return;
        }
        if (mp3File.hasID3v2Tag()) {
            AbstractID3v2Tag tag = mp3File.getID3v2Tag();
            artist = tag.getFirst(ID3v24Frames.FRAME_ID_ARTIST);
            album = tag.getFirst(ID3v24Frames.FRAME_ID_ALBUM);
            song = tag.getFirst(ID3v24Frames.FRAME_ID_TITLE);
            track = Integer.parseInt(tag.getFirst(ID3v24Frames.FRAME_ID_TRACK));
            return;
        }
        // TODO: Throw could not parse exception or do default fields.
    }

    @Override
    public String getArtist() {
        return artist;
    }

    @Override
    public String getAlbum() {
        return album;
    }

    @Override
    public String getSong() {
        return song;
    }

    @Override
    public int getTrack() {
        return track;
    }
}
