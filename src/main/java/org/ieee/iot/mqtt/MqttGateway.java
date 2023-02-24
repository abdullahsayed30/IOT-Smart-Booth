package org.ieee.iot.mqtt;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;


/*************************************************
 * Copyright (c) 2023-2-22 Abdullah Sayed Sallam.
 ************************************************/

@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {

    void senToMqtt(@Header(MqttHeaders.TOPIC) String topic, String payload);

    void sendToMqtt(String s, String state);
}
