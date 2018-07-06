package com.fortumo.bahrain.gateway.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.stubbornjava.common.Json;
import io.undertow.server.HttpServerExchange;

public interface JsonParser {

    default <T> T parseJson(HttpServerExchange exchange, TypeReference<T> typeRef) {
        return Json.serializer().fromInputStream(exchange.getInputStream(), typeRef);
    }
}
