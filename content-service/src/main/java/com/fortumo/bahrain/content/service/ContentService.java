package com.fortumo.bahrain.content.service;

import com.fortumo.bahrain.dao.DBConnection;

public class ContentService {

    public static void main(String[] args) {
        DBConnection.createTables();

        PaymentNotificationReaderTask notificationTask = new PaymentNotificationReaderTask(5);
        notificationTask.start();

        ContentDeliveryTask deliveryTask = new ContentDeliveryTask(6);
        deliveryTask.start();
    }

}
