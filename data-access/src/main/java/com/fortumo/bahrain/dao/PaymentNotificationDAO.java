package com.fortumo.bahrain.dao;

import com.fortumo.bahrain.dao.dto.PaymentNotificationDTO;
import com.fortumo.bahrain.dao.transformers.PaymentNotificationTransformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;


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

    public static List<PaymentNotificationDTO> retrievePaymentNotificationsToProcess() throws SQLException {
        List<PaymentNotificationDTO> notifications = null;

        String selectSql = "SELECT MessageID, Operator, Receiver, Sender, Text, MsgTime, IsProcessed FROM PaymentNotification WHERE " +
                "IsProcessed = " + Boolean.FALSE.toString() + "";

        ResultSet rs = DBConnection.executeQuery(selectSql);
        if(rs != null) {
            notifications = new ArrayList<>();
            while (rs.next()) {
                notifications.add(PaymentNotificationTransformer.transform(rs));
            }
        }

        return notifications;
    }

}
