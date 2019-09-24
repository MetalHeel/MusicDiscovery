package org.bram.musicdiscovery.web.server;

import com.sun.net.httpserver.HttpServer;
import org.bram.musicdiscovery.web.server.endpoints.*;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class MusicServer {
    public static void init() throws Exception {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(420), 0);
        httpServer.createContext("/ping", new PingHandler());
        httpServer.createContext("/getAllArtists", new GetAllArtistsHandler());
        httpServer.createContext("/getAllAlbumsForArtist", new GetAllAlbumsForArtistHandler());
        httpServer.createContext("/getAllSongsForArtistAndAlbum", new GetAllSongsForArtistAndAlbumHandler());
        httpServer.createContext("/streamSong", new StreamSongHandler());
        httpServer.setExecutor(Executors.newCachedThreadPool());
        httpServer.start();
    }
}
