package org.ieee.iot.mqtt;

import lombok.RequiredArgsConstructor;
import org.ieee.iot.helper.req_model.MqttMessage;
import org.ieee.iot.mqtt.MqttGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/*************************************************
 * Copyright (c) 2023-2-22 Abdullah Sayed Sallam.
 ************************************************/

@RequiredArgsConstructor
@RequestMapping("/v1/publish")
@RestController
public class MqttMessagePublisher {

    private final MqttGateway mqttGateway;

    @PostMapping
    public void sendMessage(@RequestBody MqttMessage message) {
        mqttGateway.senToMqtt(message.getTopic(), message.getPayload());
    }

}
