package com.fortumo.bahrain.content.service.http;

import java.net.URLEncoder;

public class SmsMessagePayload {

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
            e.printStackTrace();
        }
        return "";
    }
}
