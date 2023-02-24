package org.ieee.iot.mqtt;

import com.nimbusds.jose.shaded.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.stereotype.Component;

/*************************************************
 * Copyright (c) 2023-2-23 Abdullah Sayed Sallam.
 ************************************************/
@Component
@RequiredArgsConstructor
public class MqttAppMessageConverter implements MessageConverter {

    private Gson gson;

    @Override
    public Object fromMessage(Message<?> message, Class<?> targetClass) {
        return gson.toJson(message.getPayload());
    }

    @Override
    public Message<?> toMessage(Object payload, MessageHeaders headers) {

        return null;
    }
}
