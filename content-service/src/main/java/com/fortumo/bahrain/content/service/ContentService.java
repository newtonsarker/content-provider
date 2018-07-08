package com.fortumo.bahrain.content.service;

import com.fortumo.bahrain.dao.DBConnection;

public class ContentService {

    public static void main(String[] args) {
        DBConnection.createTables();
        PaymentNotificationReaderTask task = new PaymentNotificationReaderTask(5);
        task.start();
    }

}
