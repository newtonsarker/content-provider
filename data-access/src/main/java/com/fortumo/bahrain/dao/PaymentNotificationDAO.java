package com.fortumo.bahrain.dao;

import com.fortumo.bahrain.dao.dto.PaymentNotificationDTO;
import com.fortumo.bahrain.dao.transformers.PaymentNotificationTransformer;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class PaymentNotificationDAO {


    public static boolean addPaymentNotification(PaymentNotificationDTO notificationDTO) throws Exception {
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

    public static List<PaymentNotificationDTO> retrievePaymentNotificationsToProcess() throws Exception {
        List<PaymentNotificationDTO> notifications = null;

        String selectSql = "SELECT * FROM PaymentNotification WHERE IsProcessed = FALSE ";

        List<Map<String, Object>> resultSet = DBConnection.executeQuery(selectSql);
        if(resultSet != null && resultSet.size() > 0) {
            notifications = new ArrayList<>();
            for (Map<String, Object> result : resultSet) {
                notifications.add(PaymentNotificationTransformer.transform(result));
            }
        }

        return notifications;
    }

    public static boolean updatePaymentNotification(String messageID) throws Exception {
        String updateSql = "UPDATE PaymentNotification SET IsProcessed = TRUE WHERE MessageID = '" + messageID + "' ";
        return DBConnection.executeStatement(updateSql);
    }

}
