package com.fortumo.bahrain.gateway.http;

import io.undertow.server.HttpServerExchange;

import java.time.LocalDateTime;

public class PaymentNotificationRequest {

    private String messageID(HttpServerExchange exchange) {
        return Exchange.queryParams().queryParam(exchange, "message_id").orElse(null);
    }

    private String operator(HttpServerExchange exchange) {
        return Exchange.queryParams().queryParam(exchange, "operator").orElse(null);
    }

    private Integer receiver(HttpServerExchange exchange) {
        return Exchange.queryParams().queryParamAsInteger(exchange, "receiver").orElse(null);
    }

    private String sender(HttpServerExchange exchange) {
        return Exchange.queryParams().queryParam(exchange, "sender").orElse(null);
    }

    private String text(HttpServerExchange exchange) {
        return Exchange.queryParams().queryParam(exchange, "text").orElse(null);
    }

    private LocalDateTime msgTime(HttpServerExchange exchange) {
        return Exchange.queryParams().queryParamAsLocalDateTime(exchange, "timestamp").orElse(null);
    }

    public PaymentNotification getPaymentNotification(HttpServerExchange exchange) {
        PaymentNotification notification = new PaymentNotification();
        notification.setMessageID(this.messageID(exchange));
        notification.setOperator(this.operator(exchange));
        notification.setReceiver(this.receiver(exchange));
        notification.setSender(this.sender(exchange));
        notification.setText(this.text(exchange));
        notification.setMsgTime(this.msgTime(exchange));
        return notification;
    }
}
