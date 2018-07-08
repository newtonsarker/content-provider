package com.fortumo.bahrain.dao.transformers;

import com.fortumo.bahrain.dao.dto.PaymentNotificationDTO;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TimeZone;

public class PaymentNotificationTransformer {

    public static PaymentNotificationDTO transform(Map<String, Object> rs) throws Exception {
        PaymentNotificationDTO notifDTO = null;
        if(rs != null) {
            notifDTO = new PaymentNotificationDTO();
            notifDTO.setPaymentNotificationID((Long)rs.get("PaymentNotificationID".toUpperCase()));
            notifDTO.setMessageID(rs.get("MessageID".toUpperCase()).toString());
            notifDTO.setOperator(rs.get("Operator".toUpperCase()).toString());
            notifDTO.setReceiver((Integer)rs.get("Receiver".toUpperCase()));
            notifDTO.setSender(rs.get("Sender".toUpperCase()).toString());
            notifDTO.setText(rs.get("Text".toUpperCase()).toString());

            long time = ((Timestamp)rs.get("MsgTime".toUpperCase())).getTime();
            notifDTO.setMsgTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), TimeZone.getDefault().toZoneId()));

            notifDTO.setProcessed((Boolean)rs.get("IsProcessed".toUpperCase()));
        }
        return notifDTO;
    }

}
