package com.fortumo.bahrain.content.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fortumo.bahrain.content.service.http.HttpClient;
import com.fortumo.bahrain.content.service.http.HttpRequest;
import com.fortumo.bahrain.content.service.http.HttpResponse;
import com.fortumo.bahrain.content.service.http.RequestMessage;
import com.fortumo.bahrain.content.service.transformers.ContentRequestPayloadTransformer;
import com.fortumo.bahrain.content.service.transformers.ContentResponseTransformer;
import com.fortumo.bahrain.dao.ContentRequestDAO;
import com.fortumo.bahrain.dao.ContentResponseDAO;
import com.fortumo.bahrain.dao.PaymentNotificationDAO;
import com.fortumo.bahrain.dao.dto.ContentRequestDTO;
import com.fortumo.bahrain.dao.dto.ContentResponseDTO;
import com.fortumo.bahrain.dao.dto.PaymentNotificationDTO;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {

            RequestMessage request = ContentRequestPayloadTransformer.transform(notificaiton);
            ContentRequestDTO requestDTO = ContentRequestPayloadTransformer.transform(request);
            try {
                if(ContentRequestDAO.addContentRequest(requestDTO)) {
                    HttpRequest httpRequest = new HttpRequest();
                    httpRequest.setServiceURL(request.getHostUrl());
                    httpRequest.setJsonPayload(new ObjectMapper().writeValueAsString(request.getPayload()));
                    HttpResponse response = HttpClient.postJson(httpRequest);
                    ContentResponseDTO responseDTO = ContentResponseTransformer.transform(request, response);
                    ContentResponseDAO.addContentResponse(responseDTO);
                    PaymentNotificationDAO.updatePaymentNotification(requestDTO.getMessageID());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        executorService.shutdown();
    }

}
