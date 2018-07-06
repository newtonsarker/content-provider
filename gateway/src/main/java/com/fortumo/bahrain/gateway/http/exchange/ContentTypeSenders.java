package com.fortumo.bahrain.gateway.http.exchange;

import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public interface ContentTypeSenders {

    default void sendText(HttpServerExchange exchange, int statusCode, String text) {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
        exchange.setStatusCode(statusCode);
        exchange.getResponseSender().send(text);
    }

}
