package org.bram.musicdiscovery.web.server.endpoints;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.bram.musicdiscovery.files.MusicService;
import org.bram.musicdiscovery.utils.StringUtils;
import org.bram.musicdiscovery.utils.WebUtils;
import org.bram.musicdiscovery.utils.WebUtils.ContentType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StreamSongHandler extends AbstractHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String artist = WebUtils.getParameterValueFromQuery(exchange.getRequestURI().getQuery(), "artist");
        if (StringUtils.isBlank(artist)) {
            JsonObject response = new JsonObject();
            response.addProperty("error", "Artist is blank");
            writeResponse(exchange, 400, response.toString(), ContentType.APPLICATION_JSON.getType());
            return;
        }
        String album = WebUtils.getParameterValueFromQuery(exchange.getRequestURI().getQuery(), "album");
        if (StringUtils.isBlank(album)) {
            JsonObject response = new JsonObject();
            response.addProperty("error", "Album is blank");
            writeResponse(exchange, 400, response.toString(), ContentType.APPLICATION_JSON.getType());
            return;
        }
        String trackNumberString = WebUtils.getParameterValueFromQuery(exchange.getRequestURI().getQuery(), "track");
        if (StringUtils.isBlank(trackNumberString)) {
            JsonObject response = new JsonObject();
            response.addProperty("error", "Track is blank");
            writeResponse(exchange, 400, response.toString(), ContentType.APPLICATION_JSON.getType());
            return;
        }
        int trackNumber;
        try {
            trackNumber = Integer.parseInt(trackNumberString);
        } catch (NumberFormatException e) {
            JsonObject response = new JsonObject();
            response.addProperty("error", "Track needs to be a valid positive integer");
            writeResponse(exchange, 400, response.toString(), ContentType.APPLICATION_JSON.getType());
            return;
        }
        String filePath = MusicService.getTrackLocation(artist, album, trackNumber);
        if (StringUtils.isBlank(filePath)) {
            JsonObject response = new JsonObject();
            response.addProperty("error", "Track did not have corresponding file path");
            writeResponse(exchange, 400, response.toString(), ContentType.APPLICATION_JSON.getType());
            return;
        }
        if (!Files.exists(Paths.get(filePath))) {
            JsonObject response = new JsonObject();
            response.addProperty("error", "Music file does not exist");
            writeResponse(exchange, 400, response.toString(), ContentType.APPLICATION_JSON.getType());
            return;
        }
        byte[] data = Files.readAllBytes(Paths.get(filePath));
        writeResponse(exchange, 200, data, ContentType.AUDIO_MPEG.getType());
    }
}
