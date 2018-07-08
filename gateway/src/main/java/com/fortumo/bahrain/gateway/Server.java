package com.fortumo.bahrain.gateway;

import com.fortumo.bahrain.dao.DBConnection;
import com.fortumo.bahrain.gateway.handlers.RequestHandler;
import com.fortumo.bahrain.gateway.http.RestServer;

public class Server {

    // localhost:6000/api/v1/sms?message_id=e39ce00e-f8b5-4b0b-96ce-d68f94525704&operator=Etisalat&receiver=13011&sender=%2B37255555555&text=TXT+COINS&timestamp=2017-11-03+12%3A32%3A13
    public static void main(String[] args) {
        DBConnection.createTables();
        RestServer.build("0.0.0.0", 6000, RequestHandler.getSmsHandler()).start();
    }

}
