package com.fortumo.bahrain.gateway.http.exchange;

import io.undertow.server.HttpServerExchange;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Deque;
import java.util.Optional;

public interface QueryParams {

    default Optional<String> queryParam(HttpServerExchange exchange, String name) {
        return Optional.ofNullable(exchange.getQueryParameters().get(name))
                .map(Deque::getFirst);
    }

    default Optional<Integer> queryParamAsInteger(HttpServerExchange exchange, String name) {
        return queryParam(exchange, name).map(Integer::parseInt);
    }

    default Optional<LocalDateTime> queryParamAsLocalDateTime(HttpServerExchange exchange, String name) {
        return queryParam(exchange, name).map(dateStr -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return LocalDateTime.parse(dateStr, formatter);
        });
    }

}
