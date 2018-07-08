package com.fortumo.bahrain.dao.transformers;

import com.fortumo.bahrain.dao.dto.ContentResponseDTO;

import java.util.Map;

public class ContentResponseTransformer {

    public static ContentResponseDTO transform(Map<String, Object> rs) {
        ContentResponseDTO responseDTO = null;
        if(rs != null) {
            responseDTO = new ContentResponseDTO();
            responseDTO.setResponseID((Long) rs.get("responseID".toUpperCase()));
            responseDTO.setTransactionID((String) rs.get("transactionID".toUpperCase()));
            responseDTO.setMessageID((String) rs.get("messageID".toUpperCase()));
            responseDTO.setStatusCode((Integer) rs.get("statusCode".toUpperCase()));
            responseDTO.setResponseText((String) rs.get("responseText".toUpperCase()));
            responseDTO.setReceiver((String) rs.get("receiver".toUpperCase()));
            responseDTO.setOperator((String) rs.get("operator".toUpperCase()));
            responseDTO.setDelivered((Boolean) rs.get("isDelivered".toUpperCase()));
        }
        return responseDTO;
    }
}
