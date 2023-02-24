package org.ieee.iot.helper.req_model.mqtt.devices;

import lombok.Data;
import org.ieee.iot.helper.req_model.mqtt.BaseMqttModel;

/*************************************************
 * Copyright (c) 2023-2-24 Abdullah Sayed Sallam.
 ************************************************/
@Data
public class LightUpdateModel extends BaseMqttModel {
    private Long lightId;
    private boolean state;
}
