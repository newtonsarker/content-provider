package com.fortumo.bahrain.dao.dto;

import java.time.LocalDateTime;

public class PaymentNotificationDTO {

    private Long paymentNotificationID;
    private String messageID;
    private String operator;
    private Integer receiver;
    private String sender;
    private String text;
    private LocalDateTime msgTime;
    private Boolean isProcessed;

    public Long getPaymentNotificationID() {
        return paymentNotificationID;
    }

    public void setPaymentNotificationID(Long paymentNotificationID) {
        this.paymentNotificationID = paymentNotificationID;
    }

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

    public Boolean getProcessed() {
        return isProcessed;
    }

    public void setProcessed(Boolean processed) {
        isProcessed = processed;
    }

    @Override
    public String toString() {
        return "PaymentNotificationDTO{" +
                "paymentNotificationID=" + paymentNotificationID +
                ", messageID='" + messageID + '\'' +
                ", operator='" + operator + '\'' +
                ", receiver=" + receiver +
                ", sender='" + sender + '\'' +
                ", text='" + text + '\'' +
                ", msgTime=" + msgTime +
                ", isProcessed=" + isProcessed +
                '}';
    }
}
