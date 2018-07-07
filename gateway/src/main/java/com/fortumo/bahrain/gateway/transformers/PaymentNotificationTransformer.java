package com.fortumo.bahrain.gateway.transformers;

import com.fortumo.bahrain.dao.dto.PaymentNotificationDTO;
import com.fortumo.bahrain.gateway.routes.PaymentNotification;

public class PaymentNotificationTransformer {

    public static PaymentNotificationDTO transform(PaymentNotification notification) {
        if(notification == null) {
            throw new NullPointerException();
        }
        PaymentNotificationDTO dto = new PaymentNotificationDTO();
        dto.setMessageID(notification.getMessageID());
        dto.setOperator(notification.getOperator());
        dto.setReceiver(notification.getReceiver());
        dto.setSender(notification.getSender());
        dto.setText(notification.getText());
        dto.setMsgTime(notification.getMsgTime());
        dto.setProcessed(Boolean.FALSE);
        return dto;
    }

}
