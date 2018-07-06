package com.fortumo.bahrain.gateway.http;

import io.undertow.Undertow;
import io.undertow.UndertowOptions;
import io.undertow.server.HttpHandler;

public class RestServer {

    public static Undertow build(String host, int port, HttpHandler handler) {
        return Undertow.builder()
                .setServerOption(UndertowOptions.URL_CHARSET, "UTF8")
                .addHttpListener(port, host)
                .setHandler(handler)
                .build();
    }

}
