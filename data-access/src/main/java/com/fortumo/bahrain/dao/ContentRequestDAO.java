package com.fortumo.bahrain.dao;

import com.fortumo.bahrain.dao.dto.ContentRequestDTO;

public class ContentRequestDAO {

    public static boolean addContentRequest(ContentRequestDTO requestDTO) throws Exception {
        String insertSql = "INSERT INTO ContentRequest (TransactionID, MessageID, Keyword, Message) VALUES (" +
                "'" + requestDTO.getTransactionID() + "', " +
                "'" + requestDTO.getMessageID() + "', " +
                "'" + requestDTO.getKeyword() + "', " +
                "'" + requestDTO.getMessage() + "' " +
                ")";
        return DBConnection.executeStatement(insertSql);
    }

}
