package org.bram.musicdiscovery.web.server;

import com.sun.net.httpserver.HttpServer;
import org.bram.musicdiscovery.web.server.endpoints.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class MusicServer {
    public static void init() {
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(420), 0);
            httpServer.createContext("/ping", new PingHandler());
            httpServer.createContext("/getAllArtists", new GetAllArtistsHandler());
            httpServer.createContext("/getAllAlbumsForArtist", new GetAllAlbumsForArtistHandler());
            httpServer.createContext("/getAllSongsForArtistAndAlbum", new GetAllSongsForArtistAndAlbumHandler());
            httpServer.createContext("/streamSong", new StreamSongHandler());
            httpServer.setExecutor(Executors.newCachedThreadPool());
            httpServer.start();
        } catch (IOException e) {
            // TODO: Need real logging. Say couldn't get the server up and running.
            System.out.println(String.format("Could not construct server: %s", e.getMessage()));
        }
    }
}
