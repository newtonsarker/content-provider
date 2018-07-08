package com.fortumo.bahrain.content.service.dto;

public class RequestMessage {

    private String hostUrl;
    private RequestMessagePayload payload;

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
