package com.fortumo.bahrain.dao;

import com.fortumo.bahrain.dao.dto.ContentResponseDTO;

public class ContentResponseDAO {

    public static boolean addContentResponse(ContentResponseDTO responseDTO) throws Exception {
        String insertSql = "INSERT INTO ContentResponse (TransactionID, MessageID, StatusCode, ResponseJson, ResponseText, Receiver, Operator, IsDelivered) VALUES (" +
                "'" + responseDTO.getTransactionID() + "', " +
                "'" + responseDTO.getMessageID() + "', " +
                "" + responseDTO.getStatusCode() + ", " +
                "'" + responseDTO.getResponseJson() + "' " +
                "'" + responseDTO.getResponseText() + "' " +
                "'" + responseDTO.getReceiver() + "' " +
                "'" + responseDTO.getOperator() + "' " +
                "'" + responseDTO.getDelivered().toString() + "' " +
                ")";
        return DBConnection.executeStatement(insertSql);
    }



}
