package com.fortumo.bahrain.gateway;

import io.undertow.server.HttpServerExchange;

public class UserRequests {

    public String email(HttpServerExchange exchange) {
        return Exchange.pathParams().pathParam(exchange, "email").orElse(null);
    }

    public User user(HttpServerExchange exchange) {
        return Exchange.body().parseJson(exchange, User.typeRef());
    }

    public void exception(HttpServerExchange exchange) {
        boolean exception = Exchange.queryParams()
                .queryParamAsBoolean(exchange, "exception")
                .orElse(false);
        if (exception) {
            throw new RuntimeException("Some random exception. Could be anything!");
        }
    }

}
