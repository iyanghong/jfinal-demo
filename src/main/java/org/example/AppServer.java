package org.example;

import com.jfinal.server.undertow.UndertowServer;

public class AppServer {
    public static void main(String[] args) {
        UndertowServer.start(AppConfig.class);
    }
}