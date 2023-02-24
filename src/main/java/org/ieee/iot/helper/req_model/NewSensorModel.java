package org.ieee.iot.helper.req_model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.ieee.iot.domain.sensors.SensorType;

/*************************************************
 * Copyright (c) 2023-2-21 Abdullah Sayed Sallam.
 ************************************************/
@Data
public class NewSensorModel {
    @NotEmpty(message = "Sensor name is required")
    private String name;
    private String description;
    private SensorType type;
}
