package com.fortumo.bahrain.gateway;

import com.fortumo.bahrain.gateway.http.SmsRoutes;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.UndertowOptions;

public class RestServer {

    // localhost:6000/api/v1/sms?message_id=e39ce00e-f8b5-4b0b-96ce-d68f94525704&operator=Etisalat&receiver=13011&sender=%2B37255555555&text=TXT+COINS&timestamp=2017-11-03+12%3A32%3A13
    public static void main(String[] args) {
        Undertow server = Undertow.builder()
                .setServerOption(UndertowOptions.URL_CHARSET, "UTF8")
                .addHttpListener(6000, "0.0.0.0")
                .setHandler(
                        Handlers.path().addPrefixPath("/api/v1",
                                Handlers.routing().get("/sms", SmsRoutes::receivePaymentNotification)
                        )
                ).build();
        server.start();
    }

}
