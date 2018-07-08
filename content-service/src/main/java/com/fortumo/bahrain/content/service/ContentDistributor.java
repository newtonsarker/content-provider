package com.fortumo.bahrain.content.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fortumo.bahrain.content.service.http.*;
import com.fortumo.bahrain.content.service.transformers.ContentResponseTransformer;
import com.fortumo.bahrain.dao.ContentResponseDAO;
import com.fortumo.bahrain.dao.dto.ContentResponseDTO;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContentDistributor {

    public static void fetchUnDeliveredContents() throws Exception {
        List<ContentResponseDTO> contents = ContentResponseDAO.retrieveContentResponseToDeliver();
        if(contents != null && contents.size() > 0) {
            contents.forEach(content -> {
                try {
                    ContentDistributor.sendContent(content);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void sendContent(ContentResponseDTO content) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {

            HttpRequest httpRequest = ContentResponseTransformer.transform(content);
            try {
                HttpResponse response = HttpClient.getUrl(httpRequest);
                System.out.println("Content Delivery Status: " + response.getStatusCode() + " Text: " + response.getResponseBody());
                ContentResponseDAO.updateContentResponse(content.getMessageID());

            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        executorService.shutdown();
    }

}
