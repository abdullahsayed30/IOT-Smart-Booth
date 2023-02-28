package org.ieee.iot.service.user;

import org.ieee.iot.domain.User;
import org.ieee.iot.domain.devices.Device;
import org.ieee.iot.domain.sensors.Sensor;
import org.ieee.iot.helper.req_model.NewUserModel;
import java.util.Map;


/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/

public interface UserService {
    Map<String, String> signupNewUser(NewUserModel userModel);
    User loginUser(String username, String password);
    Map<String, String> loginUserT(String username, String password);
    boolean hasDevice(Device device, User user);
    boolean hasSensor(Sensor sensor, User user);
}
