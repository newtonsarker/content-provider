package com.fortumo.bahrain.content.service.registry;

public class Register {

    private String routeName;
    private String httpMethod;
    private String contentType;
    private String serviceURL;

    public Register(String routeName, String httpMethod, String contentType, String serviceURL) {
        this.routeName = routeName;
        this.httpMethod = httpMethod;
        this.contentType = contentType;
        this.serviceURL = serviceURL;
    }

    public String getRouteName() {
        return routeName;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getContentType() {
        return contentType;
    }

    public String getServiceURL() {
        return serviceURL;
    }
}
