package org.bram.musicdiscovery.web.server.endpoints;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.bram.musicdiscovery.files.MusicService;

import java.io.IOException;
import java.io.OutputStream;

public class GetAllArtistsHandler extends AbstractHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        JsonObject response = new JsonObject();
        JsonArray array = new JsonArray();
        for (String artist : MusicService.getAllArtists()) {
            array.add(artist);
        }
        response.add("artists", array);
        writeResponse(exchange, 200, response.toString(), "application/json");
    }
}
