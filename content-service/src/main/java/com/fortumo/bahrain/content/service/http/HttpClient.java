package com.fortumo.bahrain.content.service.http;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;


public class HttpClient {

    public static HttpResponse postJson(HttpRequest request) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(request.getServiceURL());

        System.out.println(request.getPayload());

        StringEntity entity = new StringEntity(request.getPayload());
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json; charset=utf-8");
        httpPost.setHeader("Content-type", "application/json; charset=utf-8");

        if(request.isAuthRequired()) {
            UsernamePasswordCredentials creds = new UsernamePasswordCredentials(request.getUsername(), request.getPassword());
            httpPost.addHeader(new BasicScheme().authenticate(creds, httpPost, null));
        }

        CloseableHttpResponse response = client.execute(httpPost);
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatusCode(response.getStatusLine().getStatusCode());
        httpResponse.setResponseBody(EntityUtils.toString(response.getEntity()));
        client.close();

        return httpResponse;
    }

    public static HttpResponse getUrl(HttpRequest request) throws Exception {
        String url = request.getServiceURL() + "?" + request.getPayload();

        String header = "Basic ";
        String headerValue = request.getUsername() + ":" + request.getPassword();
        String encodedHeaderValue = Base64.encodeBase64String(headerValue.getBytes());
        String headerBasic =  header + encodedHeaderValue;

        Header authHeader = new BasicHeader("Authorization", headerBasic);
        ArrayList<Header> headers = new ArrayList<Header>();
        headers.add(authHeader);

        org.apache.http.client.HttpClient client = HttpClients.custom().setDefaultHeaders(headers).build();
        HttpUriRequest httprequest = RequestBuilder.get().setUri(url).build();
        org.apache.http.HttpResponse response = client.execute(httprequest);

        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatusCode(response.getStatusLine().getStatusCode());
        httpResponse.setResponseBody(EntityUtils.toString(response.getEntity()));

        return httpResponse;
    }


}
