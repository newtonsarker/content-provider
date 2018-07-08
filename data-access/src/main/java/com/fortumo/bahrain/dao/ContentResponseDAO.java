package com.fortumo.bahrain.dao;

import com.fortumo.bahrain.dao.dto.ContentResponseDTO;
import com.fortumo.bahrain.dao.transformers.ContentResponseTransformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContentResponseDAO {

    public static boolean addContentResponse(ContentResponseDTO responseDTO) throws Exception {
        String insertSql = "INSERT INTO ContentResponse (TransactionID, MessageID, StatusCode, ResponseText, Receiver, Operator, IsDelivered) VALUES (" +
                "'" + responseDTO.getTransactionID() + "', " +
                "'" + responseDTO.getMessageID() + "', " +
                "" + responseDTO.getStatusCode() + ", " +
                "'" + responseDTO.getResponseText() + "', " +
                "'" + responseDTO.getReceiver() + "', " +
                "'" + responseDTO.getOperator() + "', " +
                "'" + responseDTO.getDelivered().toString() + "' " +
                ")";
        return DBConnection.executeStatement(insertSql);
    }

    public static List<ContentResponseDTO> retrieveContentResponseToDeliver() throws Exception {
        List<ContentResponseDTO> contents = null;

        String selectSql = "SELECT * FROM ContentResponse WHERE IsDelivered = FALSE ";

        List<Map<String, Object>> resultSet = DBConnection.executeQuery(selectSql);
        if(resultSet != null && resultSet.size() > 0) {
            contents = new ArrayList<>();
            for (Map<String, Object> result : resultSet) {
                contents.add(ContentResponseTransformer.transform(result));
            }
        }

        return contents;
    }

    public static boolean updateContentResponse(String messageID, String deliveredMessage) throws Exception {
        String updateSql = "UPDATE ContentResponse SET IsDelivered = TRUE, DeliveredMessage ='"+ deliveredMessage +"' WHERE MessageID = '" + messageID + "' ";
        return DBConnection.executeStatement(updateSql);
    }

}
