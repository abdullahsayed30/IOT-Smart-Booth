package org.ieee.iot.helper.res_model.room;

import lombok.Data;
import org.ieee.iot.domain.devices.Light;

import java.util.List;

/*************************************************
 * Copyright (c) 2023-2-21 Abdullah Sayed Sallam.
 ************************************************/
@Data
public class RoomDetails {
    private Long id;
    private String name;
    private String description;
    private List<Light> lights;
    private List<SensorDetails> sensors;
}
