package org.bram.musicdiscovery.web.server.endpoints;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.bram.musicdiscovery.files.MusicService;
import org.bram.musicdiscovery.utils.StringUtils;
import org.bram.musicdiscovery.utils.WebUtils;
import org.bram.musicdiscovery.utils.WebUtils.ContentType;
import org.bram.musicdiscovery.web.enums.ErrorMessages;
import org.bram.musicdiscovery.web.enums.RequestParameters;
import org.bram.musicdiscovery.web.enums.ResponseParameters;

import java.io.IOException;

public class GetAllAlbumsForArtistHandler extends AbstractHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        JsonObject response = new JsonObject();
        String artist = WebUtils.getParameterValueFromQuery(exchange.getRequestURI().getQuery(), RequestParameters.ARTIST.getParameterName());
        if (StringUtils.isBlank(artist)) {
            response.addProperty("error", ErrorMessages.BLANK_ARTIST.getMessage());
            writeResponse(exchange, 400, response.toString(), ContentType.APPLICATION_JSON.getType());
            return;
        }
        JsonArray array = new JsonArray();
        for (String album : MusicService.getAllAlbumsForArtist(artist)) {
            array.add(album);
        }
        response.add(ResponseParameters.ALBUMS.getParameterName(), array);
        writeResponse(exchange, 200, response.toString(), ContentType.APPLICATION_JSON.getType());
    }
}
