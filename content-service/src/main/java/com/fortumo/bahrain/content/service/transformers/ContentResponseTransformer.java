package com.fortumo.bahrain.content.service.transformers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fortumo.bahrain.content.service.http.HttpResponse;
import com.fortumo.bahrain.content.service.http.RequestMessage;
import com.fortumo.bahrain.dao.dto.ContentResponseDTO;


public class ContentResponseTransformer {

    public static ContentResponseDTO transform(RequestMessage requestMessage, HttpResponse response) throws Exception {
        ContentResponseDTO responseDTO = new ContentResponseDTO();
        responseDTO.setTransactionID(requestMessage.getPayload().getTransactionID());
        responseDTO.setMessageID(requestMessage.getMessageID());
        responseDTO.setStatusCode(response.getStatusCode());

        JsonNode responseNode;
        try {
            responseNode = new ObjectMapper().readTree(response.getResponseBody());
            if(responseNode != null && responseNode.get("reply_message") != null) {
                responseDTO.setResponseText(responseNode.get("reply_message").asText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        responseDTO.setReceiver(requestMessage.getPayload().getSender());
        responseDTO.setOperator(requestMessage.getPayload().getOperator());
        responseDTO.setDelivered(Boolean.FALSE);

        return responseDTO;
    }

}
