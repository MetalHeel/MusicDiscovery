package org.bram.musicdiscovery.web.server.endpoints;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.bram.musicdiscovery.files.MusicService;

import java.io.IOException;
import java.util.Set;

public class GetAllArtistsHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Set<String> artists = MusicService.getAllArtists();
    }
}
