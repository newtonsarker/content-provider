package com.fortumo.bahrain.content.service.http;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestMessagePayload {

    @JsonProperty("shortcode")
    private String shortcode;

    @JsonProperty("keyword")
    private String keyword;

    @JsonProperty("message")
    private String message;

    @JsonProperty("operator")
    private String operator;

    @JsonProperty("sender")
    private String sender;

    @JsonProperty("transaction_id")
    private String transactionID;

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

}
