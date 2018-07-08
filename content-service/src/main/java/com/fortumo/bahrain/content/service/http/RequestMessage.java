package com.fortumo.bahrain.content.service.http;

public class RequestMessage {

    private String messageID;
    private String hostUrl;
    private RequestMessagePayload payload;

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public RequestMessagePayload getPayload() {
        return payload;
    }

    public void setPayload(RequestMessagePayload payload) {
        this.payload = payload;
    }
}
