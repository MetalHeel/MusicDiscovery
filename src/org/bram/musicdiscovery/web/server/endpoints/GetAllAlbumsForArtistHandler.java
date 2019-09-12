package org.bram.musicdiscovery.web.server.endpoints;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.bram.musicdiscovery.files.MusicService;
import org.bram.musicdiscovery.utils.StringUtils;
import org.bram.musicdiscovery.utils.WebUtils;

import java.io.IOException;

public class GetAllAlbumsForArtistHandler extends AbstractHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        JsonObject response = new JsonObject();
        String artist = WebUtils.getParameterValueFromQuery(exchange.getRequestURI().getQuery(), "artist");
        if (StringUtils.isBlank(artist)) {
            response.addProperty("error", "Artist is blank");
            writeResponse(exchange, 400, response.toString(), "application/json");
            return;
        }
        JsonArray array = new JsonArray();
        for (String album : MusicService.getAllAlbumsForArtist(artist)) {
            array.add(album);
        }
        response.add("albums", array);
        writeResponse(exchange, 200, response.toString(), "application/json");
    }
}
