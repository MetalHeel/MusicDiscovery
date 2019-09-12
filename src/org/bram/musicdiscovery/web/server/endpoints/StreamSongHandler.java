package org.bram.musicdiscovery.web.server.endpoints;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.bram.musicdiscovery.files.MusicService;
import org.bram.musicdiscovery.utils.StringUtils;
import org.bram.musicdiscovery.utils.WebUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StreamSongHandler extends AbstractHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String artist = WebUtils.getParameterValueFromQuery(exchange.getRequestURI().getQuery(), "artist");
        if (StringUtils.isBlank(artist)) {
            JsonObject response = new JsonObject();
            response.addProperty("error", "Artist is blank");
            writeResponse(exchange, 400, response.toString(), "application/json");
            return;
        }
        String album = WebUtils.getParameterValueFromQuery(exchange.getRequestURI().getQuery(), "album");
        if (StringUtils.isBlank(album)) {
            JsonObject response = new JsonObject();
            response.addProperty("error", "Album is blank");
            writeResponse(exchange, 400, response.toString(), "application/json");
            return;
        }
        // TODO: If not a number.
        String trackNumberString = WebUtils.getParameterValueFromQuery(exchange.getRequestURI().getQuery(), "track");
        if (StringUtils.isBlank(trackNumberString)) {
            JsonObject response = new JsonObject();
            response.addProperty("error", "Track is blank");
            writeResponse(exchange, 400, response.toString(), "application/json");
            return;
        }
        int trackNumber = Integer.parseInt(trackNumberString);
        // TODO: If path blank.
        String filePath = MusicService.getTrackLocation(artist, album, trackNumber);
        // TODO: If file doesn't exist.
        byte[] data = Files.readAllBytes(Paths.get(filePath));
        writeResponse(exchange, 200, data, "audio/mpeg");
    }
}
