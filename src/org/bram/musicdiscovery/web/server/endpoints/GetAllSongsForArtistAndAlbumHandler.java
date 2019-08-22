package org.bram.musicdiscovery.web.server.endpoints;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.bram.musicdiscovery.files.MusicService;
import org.bram.musicdiscovery.utils.WebUtils;

import java.io.IOException;
import java.io.OutputStream;

public class GetAllSongsForArtistAndAlbumHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String artist = WebUtils.getParameterValueFromQuery(exchange.getRequestURI().getQuery(), "artist");
        String album = WebUtils.getParameterValueFromQuery(exchange.getRequestURI().getQuery(), "album");
        JsonArray array = new JsonArray();
        for (String song : MusicService.getAllSongsForArtistAndAlbum(artist, album)) {
            array.add(song);
        }
        JsonObject response = new JsonObject();
        response.add("songs", array);
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, response.toString().length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.toString().getBytes());
        os.close();
    }
}
