package com.fortumo.bahrain.content.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PaymentNotificationReaderTask {

    int seconds;

    public PaymentNotificationReaderTask(int seconds) {
        this.seconds = seconds;
    }

    public void start() {
        ScheduledExecutorService execService = Executors.newScheduledThreadPool(5);
        execService.scheduleAtFixedRate(()->{
            System.out.println("hi there at: "+ new java.util.Date());
        }, 5, this.seconds, TimeUnit.SECONDS);
    }

}
