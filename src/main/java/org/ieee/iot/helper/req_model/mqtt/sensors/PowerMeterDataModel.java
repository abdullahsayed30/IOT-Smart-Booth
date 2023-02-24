package org.ieee.iot.helper.req_model.mqtt.sensors;

import lombok.Data;
import org.ieee.iot.helper.req_model.mqtt.BaseMqttModel;

/*************************************************
 * Copyright (c) 2023-2-24 Abdullah Sayed Sallam.
 ************************************************/
@Data
public class PowerMeterDataModel extends BaseMqttModel {
    private Long sensorId;
    private Integer current;
    private Integer voltage;
}
