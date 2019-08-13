package org.bram.musicdiscovery.web.server;

import com.sun.net.httpserver.HttpServer;
import org.bram.musicdiscovery.web.server.endpoints.PingHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class MusicServer {
    HttpServer httpServer;

    public MusicServer() throws IOException {
        httpServer = HttpServer.create(new InetSocketAddress(0420), 0);
        httpServer.createContext("/ping", new PingHandler());
        // TODO: Figure out Executors.
        httpServer.setExecutor(Executors.newCachedThreadPool());
    }

    public void listen() {
        httpServer.start();
    }

    public void stopListening() {
        httpServer.stop(0);
    }
}
