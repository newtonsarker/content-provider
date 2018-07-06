package com.fortumo.bahrain.gateway.routes;

import com.fortumo.bahrain.gateway.http.exchange.Exchange;
import io.undertow.server.HttpServerExchange;

public class SmsRoutes {

    private static final PaymentNotificationRequest request = new PaymentNotificationRequest();

    public static void receivePaymentNotification(HttpServerExchange exchange) {
        PaymentNotification notification = null;
        try {
            notification = request.getPaymentNotification(exchange);
            Exchange.body().sendText(exchange, 200, "OK");
        } catch (Exception e) {
            Exchange.body().sendText(exchange, 500, "OK");
        }
    }

}
