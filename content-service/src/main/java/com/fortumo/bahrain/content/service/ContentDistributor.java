package com.fortumo.bahrain.content.service;

import com.fortumo.bahrain.dao.PaymentNotificationDAO;
import com.fortumo.bahrain.dao.dto.PaymentNotificationDTO;

import java.sql.SQLException;
import java.util.List;

public class ContentDistributor {

    public static void fetchUnprocessedNotifications() {

        List<PaymentNotificationDTO> notifications = null;
        try {
            notifications = PaymentNotificationDAO.retrievePaymentNotificationsToProcess();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(notifications != null && notifications.size() > 0) {
            notifications.forEach(notificaiton -> ContentDistributor.forwardNotification(notificaiton));
        }

    }


    public static void forwardNotification(PaymentNotificationDTO notificaiton) {

    }

}
