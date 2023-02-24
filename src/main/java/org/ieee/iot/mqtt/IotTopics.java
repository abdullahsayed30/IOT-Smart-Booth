package org.ieee.iot.mqtt;

/*************************************************
 * Copyright (c) 2023-2-22 Abdullah Sayed Sallam.
 ************************************************/

public enum IotTopics {
    LIGHT("/device/light"), TEMP_HUM("/sensor/temp_hum"), POWER_METER("/sensor/power_meter");
    private final String topic;
    IotTopics(String topic) {
        this.topic = topic;
    }
    public String getTopic() {
        return topic;
    }
}
