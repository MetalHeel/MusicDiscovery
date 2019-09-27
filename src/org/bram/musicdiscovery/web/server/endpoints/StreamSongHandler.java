package org.bram.musicdiscovery.web.server.endpoints;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.bram.musicdiscovery.files.MusicService;
import org.bram.musicdiscovery.utils.StringUtils;
import org.bram.musicdiscovery.utils.WebUtils;
import org.bram.musicdiscovery.utils.WebUtils.ContentType;
import org.bram.musicdiscovery.web.enums.ErrorMessages;
import org.bram.musicdiscovery.web.enums.RequestParameters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StreamSongHandler extends AbstractHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String artist = WebUtils.getParameterValueFromQuery(exchange.getRequestURI().getQuery(), RequestParameters.ARTIST.getParameterName());
        if (StringUtils.isBlank(artist)) {
            JsonObject response = new JsonObject();
            response.addProperty("error", ErrorMessages.BLANK_ARTIST.getMessage());
            writeResponse(exchange, 400, response.toString(), ContentType.APPLICATION_JSON.getType());
            return;
        }
        String album = WebUtils.getParameterValueFromQuery(exchange.getRequestURI().getQuery(), RequestParameters.ALBUM.getParameterName());
        if (StringUtils.isBlank(album)) {
            JsonObject response = new JsonObject();
            response.addProperty("error", ErrorMessages.BLANK_ALBUM.getMessage());
            writeResponse(exchange, 400, response.toString(), ContentType.APPLICATION_JSON.getType());
            return;
        }
        String trackNumberString = WebUtils.getParameterValueFromQuery(exchange.getRequestURI().getQuery(), RequestParameters.TRACK.getParameterName());
        if (StringUtils.isBlank(trackNumberString)) {
            JsonObject response = new JsonObject();
            response.addProperty("error", ErrorMessages.BLANK_TRACK.getMessage());
            writeResponse(exchange, 400, response.toString(), ContentType.APPLICATION_JSON.getType());
            return;
        }
        int trackNumber;
        try {
            trackNumber = Integer.parseInt(trackNumberString);
        } catch (NumberFormatException e) {
            JsonObject response = new JsonObject();
            response.addProperty("error", ErrorMessages.INVALID_INTEGER.getMessage());
            writeResponse(exchange, 400, response.toString(), ContentType.APPLICATION_JSON.getType());
            return;
        }
        String filePath = MusicService.getTrackLocation(artist, album, trackNumber);
        if (StringUtils.isBlank(filePath)) {
            JsonObject response = new JsonObject();
            response.addProperty("error", ErrorMessages.MISSING_PATH.getMessage());
            writeResponse(exchange, 400, response.toString(), ContentType.APPLICATION_JSON.getType());
            return;
        }
        if (!Files.exists(Paths.get(filePath))) {
            JsonObject response = new JsonObject();
            response.addProperty("error", ErrorMessages.MISSING_FILE.getMessage());
            writeResponse(exchange, 400, response.toString(), ContentType.APPLICATION_JSON.getType());
            return;
        }
        byte[] data = Files.readAllBytes(Paths.get(filePath));
        writeResponse(exchange, 200, data, ContentType.AUDIO_MPEG.getType());
    }
}
