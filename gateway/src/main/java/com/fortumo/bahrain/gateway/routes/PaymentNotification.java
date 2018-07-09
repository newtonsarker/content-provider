package com.fortumo.bahrain.gateway.routes;

import java.time.LocalDateTime;

public class PaymentNotification {

    private String messageID;
    private String operator;
    private Integer receiver;
    private String sender;
    private String text;
    private LocalDateTime msgTime;

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(LocalDateTime msgTime) {
        this.msgTime = msgTime;
    }

    @Override
    public String toString() {
        return "PaymentNotification{" +
                "messageID='" + messageID + '\'' +
                ", operator='" + operator + '\'' +
                ", receiver=" + receiver +
                ", sender='" + sender + '\'' +
                ", text='" + text + '\'' +
                ", msgTime=" + msgTime +
                '}';
    }
}