package com.fortumo.bahrain.content.service.transformers;

import com.fortumo.bahrain.content.service.dto.RequestMessage;
import com.fortumo.bahrain.content.service.dto.RequestMessagePayload;
import com.fortumo.bahrain.content.service.registry.ServiceReristry;
import com.fortumo.bahrain.dao.dto.PaymentNotificationDTO;

import java.util.Date;

public class PayloadTransformer {

    public static RequestMessage transform(PaymentNotificationDTO notificaiton) {
        RequestMessagePayload payload = new RequestMessagePayload();
        payload.setTransactionID(new Date().getTime() + "");
        payload.setShortcode(notificaiton.getReceiver().toString());
        payload.setKeyword(notificaiton.getText().split(" ")[0]);
        payload.setMessage(notificaiton.getText());
        payload.setOperator(notificaiton.getOperator());
        payload.setSender(notificaiton.getSender());

        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setHostUrl(ServiceReristry.getRegister(payload.getKeyword().toUpperCase()).getServiceURL());
        requestMessage.setPayload(payload);

        return requestMessage;
    }

}
