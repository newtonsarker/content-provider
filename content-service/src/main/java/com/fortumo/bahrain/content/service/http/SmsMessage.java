package com.fortumo.bahrain.content.service.http;

public class SmsMessage {

    private String hostUrl;
    private String username;
    private String password;
    private SmsMessagePayload payload;


    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SmsMessagePayload getPayload() {
        return payload;
    }

    public void setPayload(SmsMessagePayload payload) {
        this.payload = payload;
    }
}
