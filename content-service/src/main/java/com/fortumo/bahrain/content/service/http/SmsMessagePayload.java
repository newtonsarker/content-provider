package com.fortumo.bahrain.content.service.http;

import com.fortumo.bahrain.content.service.transformers.ContentResponseTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;

public class SmsMessagePayload {

    private static final Logger logger = LoggerFactory.getLogger(SmsMessagePayload.class);

    private String message;
    private String messageID;
    private String receiver;
    private String operator;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String toQueryParameter() {
        try {
            return "message=" + URLEncoder.encode(message, "UTF-8") + "&mo_message_id=" + messageID + "&receiver=" + receiver + "&operator=" + operator;
        } catch (Exception e) {
            logger.error("UTF-8 encoding failed", e);
        }
        return "";
    }
}
