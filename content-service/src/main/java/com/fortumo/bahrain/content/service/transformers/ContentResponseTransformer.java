package com.fortumo.bahrain.content.service.transformers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fortumo.bahrain.content.service.http.*;
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

    public static HttpRequest transform(ContentResponseDTO content) {

        SmsMessagePayload payload = new SmsMessagePayload();
        if(200 == content.getStatusCode()  && "this is the reply message content".equalsIgnoreCase(content.getResponseText())) {
            payload.setMessage("this is the reply message content");
        } else {
            payload.setMessage("Something went wrong. Please contact us at s.fortumo.com to receive your service");
        }
        payload.setMessageID(content.getMessageID());
        payload.setReceiver(content.getReceiver());
        payload.setOperator(content.getOperator());

        HttpRequest message = new HttpRequest();
        message.setServiceURL("https://bratwurst.fortumo.mobi/sms/send");
        message.setUsername("fortumo");
        message.setPassword("topsecret");
        message.setPayload(payload.toQueryParameter());

        return message;
    }
}
