package com.fortumo.bahrain.content.service;

import com.fortumo.bahrain.dao.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ContentDeliveryTask {

    private static final Logger logger = LoggerFactory.getLogger(ContentDeliveryTask.class);

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
                logger.error("Content delivery task failed to start");
            }
        }, 4, this.seconds, TimeUnit.SECONDS);
    }

}
