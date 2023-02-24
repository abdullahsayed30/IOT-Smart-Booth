package org.ieee.iot.service.device;

import org.ieee.iot.domain.Place;
import org.ieee.iot.domain.Room;
import org.ieee.iot.domain.devices.Light;

/*************************************************
 * Copyright (c) 2023-2-19 Abdullah Sayed Sallam.
 ************************************************/

public interface DeviceService {
    Light createLight(String name, String description, Room room);
    boolean updateLightState(Long lightId, boolean state);
}
