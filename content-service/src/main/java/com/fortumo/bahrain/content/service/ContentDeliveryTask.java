package com.fortumo.bahrain.content.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ContentDeliveryTask {

    int seconds;

    public ContentDeliveryTask(int seconds) {
        this.seconds = seconds;
    }

    public void start() {
        ScheduledExecutorService execService = Executors.newScheduledThreadPool(5);
        execService.scheduleAtFixedRate(()->{
            try {
                ContentDistributor.fetchUnDeliveredContents();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 4, this.seconds, TimeUnit.SECONDS);
    }

}
