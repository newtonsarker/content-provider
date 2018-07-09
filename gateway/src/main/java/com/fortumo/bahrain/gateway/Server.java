package com.fortumo.bahrain.gateway;

import com.fortumo.bahrain.dao.DBConnection;
import com.fortumo.bahrain.gateway.handlers.RequestHandler;
import com.fortumo.bahrain.gateway.http.RestServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Struct;
import java.util.Date;

public class Server {

    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) {
        logger.info("Gateway started at " + new Date().toString());

        DBConnection.createTables();

        String host = "0.0.0.0";
        Integer port = 6000;
        RestServer.build(host, port, RequestHandler.getSmsHandler()).start();

        logger.info("Listening to " + host + ":" + port);
    }

}
