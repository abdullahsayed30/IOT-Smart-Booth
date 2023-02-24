package org.ieee.iot.service.booth;

import org.ieee.iot.domain.Booth;
import org.ieee.iot.domain.User;
import org.ieee.iot.domain.devices.Device;
import org.ieee.iot.domain.devices.Light;
import org.ieee.iot.domain.sensors.Sensor;
import org.ieee.iot.helper.req_model.NewDeviceModel;
import org.ieee.iot.helper.req_model.NewSensorModel;

import java.util.Map;


/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/

public interface BoothService {
    Booth createBoothForNewUser(User user);
    Map<String, Object> getBoothDetails(User user);

    Device addDeviceToBooth(User user, NewDeviceModel deviceModel);
    Sensor addSensorToBooth(User user, NewSensorModel sensorModel);

    Light addLightToBooth(User user, NewDeviceModel deviceModel);

}
