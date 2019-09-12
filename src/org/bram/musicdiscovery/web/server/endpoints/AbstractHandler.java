package org.bram.musicdiscovery.web.server.endpoints;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public abstract class AbstractHandler {
    public void writeResponse(HttpExchange exchange, int responseCode, String responseBody, String contentType) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", contentType);
        exchange.sendResponseHeaders(responseCode, responseBody.length());
        OutputStream os = exchange.getResponseBody();
        os.write(responseBody.getBytes());
        os.close();
    }

    public void writeResponse(HttpExchange exchange, int responseCode, byte[] responseBody, String contentType) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", contentType);
        exchange.sendResponseHeaders(responseCode, responseBody.length);
        OutputStream os = exchange.getResponseBody();
        os.write(responseBody);
        os.close();
    }
}
