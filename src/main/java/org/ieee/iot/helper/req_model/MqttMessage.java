package org.ieee.iot.helper.req_model;

import lombok.Data;


/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/

@Data
public class MqttMessage {
    private String topic;
    private String payload;
}
