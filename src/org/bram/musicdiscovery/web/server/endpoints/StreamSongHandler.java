package org.bram.musicdiscovery.web.server.endpoints;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.bram.musicdiscovery.files.MusicService;
import org.bram.musicdiscovery.utils.WebUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StreamSongHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String artist = WebUtils.getParameterValueFromQuery(exchange.getRequestURI().getQuery(), "artist");
        String album = WebUtils.getParameterValueFromQuery(exchange.getRequestURI().getQuery(), "album");
        // TODO: If not a number.
        int trackNumber = Integer.parseInt(WebUtils.getParameterValueFromQuery(exchange.getRequestURI().getQuery(), "track"));
        // TODO: If path blank.
        String filePath = MusicService.getTrackLocation(artist, album, trackNumber);
        // TODO: If file doesn't exist.
        byte[] data = Files.readAllBytes(Paths.get(filePath));
        exchange.getResponseHeaders().set("Content-Type", "audio/mpeg");
        exchange.sendResponseHeaders(200, data.length);
        OutputStream os = exchange.getResponseBody();
        os.write(data);
        os.close();
    }
}
