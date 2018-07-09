package com.fortumo.bahrain.content.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PaymentNotificationReaderTask {

    private static final Logger logger = LoggerFactory.getLogger(PaymentNotificationReaderTask.class);
    int seconds;

    public PaymentNotificationReaderTask(int seconds) {
        this.seconds = seconds;
    }

    public void start() {
        ScheduledExecutorService execService = Executors.newScheduledThreadPool(5);
        execService.scheduleAtFixedRate(()->{
            try {
                ContentRequestor.fetchUnprocessedNotifications();
            } catch (Exception e) {
                logger.error("Failed to start Notification reader task", e);
            }
        }, 3, this.seconds, TimeUnit.SECONDS);
    }

}
