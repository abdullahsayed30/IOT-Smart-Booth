package org.ieee.iot.service.room;

import org.ieee.iot.domain.Room;
import org.ieee.iot.domain.User;
import org.ieee.iot.domain.devices.Device;
import org.ieee.iot.domain.devices.Light;
import org.ieee.iot.domain.sensors.Sensor;
import org.ieee.iot.helper.req_model.NewDeviceModel;
import org.ieee.iot.helper.req_model.NewRoomModel;
import org.ieee.iot.helper.req_model.NewSensorModel;

import java.util.ArrayList;
import java.util.Map;


/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/

public interface RoomService {
    Room createRoom(User user, NewRoomModel newRoomModel);
    ArrayList<Map<String, Object>> getRooms(User user);

    Device addDeviceToRoom(User user, Long roomId, NewDeviceModel deviceModel);
    Sensor addSensorToRoom(User user, Long roomId, NewSensorModel sensorModel);

    Light addLightToRoom(User user, Long roomId, NewDeviceModel deviceModel);

}
