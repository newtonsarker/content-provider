package com.fortumo.bahrain.gateway.handlers;

import com.fortumo.bahrain.gateway.routes.SmsRoutes;
import io.undertow.Handlers;
import io.undertow.server.HttpHandler;

public class RequestHandler {

    public static HttpHandler getSmsHandler() {
        return Handlers.path().addPrefixPath("/api/v1", Handlers.routing().get("/sms", SmsRoutes::receivePaymentNotification));
    }

}
