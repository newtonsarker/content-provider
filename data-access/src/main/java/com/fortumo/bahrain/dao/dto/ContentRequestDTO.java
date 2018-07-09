package com.fortumo.bahrain.dao.dto;

public class ContentRequestDTO {

    private Integer requestID;
    private String transactionID;
    private String messageID;
    private String keyword;
    private String message;

    public Integer getRequestID() {
        return requestID;
    }

    public void setRequestID(Integer requestID) {
        this.requestID = requestID;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ContentRequestDTO{" +
                "requestID=" + requestID +
                ", transactionID='" + transactionID + '\'' +
                ", messageID='" + messageID + '\'' +
                ", keyword='" + keyword + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
