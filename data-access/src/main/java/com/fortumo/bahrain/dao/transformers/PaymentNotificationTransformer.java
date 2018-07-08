package com.fortumo.bahrain.dao.transformers;

import com.fortumo.bahrain.dao.dto.PaymentNotificationDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class PaymentNotificationTransformer {

    public static PaymentNotificationDTO transform(ResultSet rs) throws SQLException {
        PaymentNotificationDTO notifDTO = null;
        if(rs != null) {
            notifDTO = new PaymentNotificationDTO();
            notifDTO.setPaymentNotificationID(rs.getLong("PaymentNotificationID"));
            notifDTO.setMessageID(rs.getString("MessageID"));
            notifDTO.setOperator(rs.getString("Operator"));
            notifDTO.setReceiver(rs.getInt("Receiver"));
            notifDTO.setSender(rs.getString("Sender"));
            notifDTO.setText(rs.getString("Text"));
            notifDTO.setMsgTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(rs.getTime("MsgTime").getTime()), TimeZone.getDefault().toZoneId()));
            notifDTO.setProcessed(rs.getBoolean("IsProcessed"));
        }
        return notifDTO;
    }

}
