package com.fortumo.bahrain.content.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fortumo.bahrain.content.service.http.HttpClient;
import com.fortumo.bahrain.content.service.http.HttpRequest;
import com.fortumo.bahrain.content.service.http.HttpResponse;
import com.fortumo.bahrain.content.service.http.RequestMessage;
import com.fortumo.bahrain.content.service.transformers.ContentRequestPayloadTransformer;
import com.fortumo.bahrain.dao.ContentRequestDAO;
import com.fortumo.bahrain.dao.PaymentNotificationDAO;
import com.fortumo.bahrain.dao.dto.ContentRequestDTO;
import com.fortumo.bahrain.dao.dto.PaymentNotificationDTO;

import java.util.List;

public class ContentRequestor {

    public static void fetchUnprocessedNotifications() throws Exception {
        List<PaymentNotificationDTO> notifications = PaymentNotificationDAO.retrievePaymentNotificationsToProcess();
        if(notifications != null && notifications.size() > 0) {
            notifications.forEach(notificaiton -> {
                try {
                    ContentRequestor.forwardNotification(notificaiton);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void forwardNotification(PaymentNotificationDTO notificaiton) throws Exception {
        RequestMessage request = ContentRequestPayloadTransformer.transform(notificaiton);
        ContentRequestDTO requestDTO = ContentRequestPayloadTransformer.transform(request);
        if(ContentRequestDAO.addContentRequest(requestDTO)) {
            HttpRequest httpRequest = new HttpRequest();
            httpRequest.setServiceURL(request.getHostUrl());
            httpRequest.setJsonPayload(new ObjectMapper().writeValueAsString(request.getPayload()));
            HttpResponse response = HttpClient.postJson(httpRequest);
        }
    }

}
