package com.fortumo.bahrain.content.service;

public class ContentService {

    public static void main(String[] args) {
        PaymentNotificationReaderTask task = new PaymentNotificationReaderTask(3);
        task.start();
    }

}
