package com.fortumo.bahrain.dao.dto;

public class ContentResponseDTO {

    private Long responseID;
    private String transactionID;
    private String messageID;
    private Integer statusCode;
    private String responseText;
    private String receiver;
    private String operator;
    private Boolean isDelivered;

    public Long getResponseID() {
        return responseID;
    }

    public void setResponseID(Long responseID) {
        this.responseID = responseID;
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

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
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

    public Boolean getDelivered() {
        return isDelivered;
    }

    public void setDelivered(Boolean delivered) {
        isDelivered = delivered;
    }

    @Override
    public String toString() {
        return "ContentResponseDTO{" +
                "responseID=" + responseID +
                ", transactionID='" + transactionID + '\'' +
                ", messageID='" + messageID + '\'' +
                ", statusCode=" + statusCode +
                ", responseText='" + responseText + '\'' +
                ", receiver='" + receiver + '\'' +
                ", operator='" + operator + '\'' +
                ", isDelivered=" + isDelivered +
                '}';
    }

}
