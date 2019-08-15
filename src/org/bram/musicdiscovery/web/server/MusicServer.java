package org.bram.musicdiscovery.web.server;

import com.sun.net.httpserver.HttpServer;
import org.bram.musicdiscovery.web.server.endpoints.PingHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class MusicServer {
    HttpServer httpServer;

    public MusicServer() {
        try {
            httpServer = HttpServer.create(new InetSocketAddress(420), 0);
            httpServer.createContext("/ping", new PingHandler());
            // TODO: Figure out Executors.
            httpServer.setExecutor(null);
        } catch (IOException e) {
            // TODO: Need real logging. Say couldn't get the server up and running.
            System.out.println(String.format("Could not construct server: %s", e.getMessage()));
        }
    }

    public void listen() {
        httpServer.start();
    }

    public void stopListening() {
        httpServer.stop(0);
    }
}
