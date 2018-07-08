package com.fortumo.bahrain.content.service.http;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class HttpClient {

    public static HttpResponse postJson(HttpRequest request) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(request.getServiceURL());

        StringEntity entity = new StringEntity(request.getJsonPayload());
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

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

}
