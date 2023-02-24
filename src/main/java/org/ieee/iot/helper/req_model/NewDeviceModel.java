package org.ieee.iot.helper.req_model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.ieee.iot.domain.devices.DeviceType;

/*************************************************
 * Copyright (c) 2023-2-19 Abdullah Sayed Sallam.
 ************************************************/

@Data
public class NewDeviceModel {
    @NotEmpty(message = "Device name is required")
    private String name;
    private String description;
    private DeviceType type;
}
