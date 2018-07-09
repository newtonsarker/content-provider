package com.fortumo.bahrain.content.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fortumo.bahrain.content.service.http.*;
import com.fortumo.bahrain.content.service.transformers.ContentResponseTransformer;
import com.fortumo.bahrain.dao.ContentResponseDAO;
import com.fortumo.bahrain.dao.dto.ContentResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContentDistributor {

    private static final Logger logger = LoggerFactory.getLogger(ContentDistributor.class);

    public static void fetchUnDeliveredContents() throws Exception {
        List<ContentResponseDTO> contents = ContentResponseDAO.retrieveContentResponseToDeliver();
        if(contents != null && contents.size() > 0) {
            contents.forEach(content -> {
                try {
                    ContentDistributor.sendContent(content);
                } catch (Exception e) {
                    logger.error("Failed to send content to user", e);
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
                logger.info("Content Delivery Status: " + response.getStatusCode() + " Text: " + response.getResponseBody());
                ContentResponseDAO.updateContentResponse(content.getTransactionID(), httpRequest.getContentMessage());
            } catch (Exception e) {
                logger.error("Failed to deliver content to user");
            }
        });
        executorService.shutdown();
    }

}
