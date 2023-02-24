package org.ieee.iot.helper.req_model.mqtt.sensors;

import lombok.Data;
import org.ieee.iot.helper.req_model.mqtt.BaseMqttModel;

/*************************************************
 * Copyright (c) 2023-2-24 Abdullah Sayed Sallam.
 ************************************************/
@Data
public class TmpHumDataModel extends BaseMqttModel {
    private Long sensorId;
    private Integer temperature;
    private Integer humidity;
}
