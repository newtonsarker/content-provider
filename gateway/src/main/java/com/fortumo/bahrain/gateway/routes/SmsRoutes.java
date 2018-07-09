package com.fortumo.bahrain.gateway.routes;

import com.fortumo.bahrain.dao.PaymentNotificationDAO;
import com.fortumo.bahrain.gateway.http.exchange.Exchange;
import com.fortumo.bahrain.gateway.transformers.PaymentNotificationTransformer;
import io.undertow.server.HttpServerExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmsRoutes {

    private static final Logger logger = LoggerFactory.getLogger(SmsRoutes.class);

    private static final PaymentNotificationRequest request = new PaymentNotificationRequest();

    public static void receivePaymentNotification(HttpServerExchange exchange) {
        PaymentNotification notification;
        try {
            notification = request.getPaymentNotification(exchange);
            logger.info("Request received - " + notification.toString());
            if(PaymentNotificationDAO.addPaymentNotification(PaymentNotificationTransformer.transform(notification))){
                Exchange.body().sendText(exchange, 200, "OK");
            } else {
                Exchange.body().sendText(exchange, 500, "Bad Request!");
            }
        } catch (Exception e) {
            logger.error("Failed to parse payment notification request", e);
            Exchange.body().sendText(exchange, 500, "Bad Request!");
        }
    }

}
