package com.fortumo.bahrain.dao;

import com.fortumo.bahrain.dao.dto.PaymentNotificationDTO;

import java.time.format.DateTimeFormatter;


public class PaymentNotificationDAO {

    public static boolean addPaymentNotification(PaymentNotificationDTO notificationDTO) {
        DBConnection.executeStatement("CREATE TABLE IF NOT EXISTS PaymentNotification ( PaymentNotificationID IDENTITY PRIMARY KEY, MessageID VARCHAR(50) NULL, Operator VARCHAR(50) NULL, Receiver INT NULL, Sender VARCHAR(50) NULL, Text VARCHAR(50) NULL, MsgTime TIMESTAMP NULL, IsProcessed BOOLEAN NULL )");
        String insertSql = "INSERT INTO PaymentNotification (MessageID, Operator, Receiver, Sender, Text, MsgTime, IsProcessed) VALUES (" +
                "'" + notificationDTO.getMessageID() + "', " +
                "'" + notificationDTO.getOperator() + "', " +
                "" + notificationDTO.getReceiver() + ", " +
                "'" + notificationDTO.getSender() + "', " +
                "'" + notificationDTO.getText() + "', " +
                "PARSEDATETIME('" + notificationDTO.getMsgTime().format(DateTimeFormatter.ofPattern("dd-MM-yy hh:mm:ss")) + "', 'dd-MM-yy hh:mm:ss')," +
                "" + notificationDTO.getProcessed().toString() + "" +
                ")";
        return DBConnection.executeStatement(insertSql);
    }

}
