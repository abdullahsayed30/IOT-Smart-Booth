package org.ieee.iot.service.room;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ieee.iot.utils.db.sequence.SequenceGenerator;
import org.ieee.iot.domain.House;
import org.ieee.iot.domain.Room;
import org.ieee.iot.domain.User;
import org.ieee.iot.domain.devices.Device;
import org.ieee.iot.domain.devices.Light;
import org.ieee.iot.domain.sensors.PowerMeterSensorData;
import org.ieee.iot.domain.sensors.Sensor;
import org.ieee.iot.domain.sensors.SensorType;
import org.ieee.iot.domain.sensors.TempHumSensorData;
import org.ieee.iot.helper.req_model.NewDeviceModel;
import org.ieee.iot.helper.req_model.NewRoomModel;
import org.ieee.iot.helper.req_model.NewSensorModel;
import org.ieee.iot.repository.RoomRepository;
import org.ieee.iot.service.device.DeviceService;
import org.ieee.iot.service.sensor.SensorService;
import org.ieee.iot.service.user.UserService;
import org.springframework.stereotype.Service;

import java.util.*;


/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService {
    private final SequenceGenerator sequenceGenerator;
    private final RoomRepository roomRepository;
    private final UserService userService;
    private final DeviceService deviceService;
    private final SensorService sensorService;



    @Override
    public ArrayList<Map<String, Object>> getRooms(User user) {
        ArrayList<Map<String, Object>> rooms = new ArrayList<>();

        user.getHouse().getRooms().forEach(room -> {
            Map<String, Object> roomDetails = new LinkedHashMap<>();

            roomDetails.put("id", room.getId());
            roomDetails.put("name", room.getName());
            roomDetails.put("description", room.getDescription());
            roomDetails.put("lights", room.getLights());
            roomDetails.put("sensors", new ArrayList<>() {
                {
                    room.getSensors().forEach(sensor -> {
                        Map<String, Object> sensorDetails = new LinkedHashMap<>();
                        sensorDetails.put("id", sensor.getId());
                        sensorDetails.put("name", sensor.getName());
                        sensorDetails.put("description", sensor.getDescription());
                        sensorDetails.put("type", sensor.getType());

                        SensorType sensorType = sensor.getType();

                        switch (sensorType) {
                            case TEMP_HUM -> sensorDetails.put("data", new HashMap<>() {
                                {
                                    TempHumSensorData sensorData = sensorService.getTempHumSensorReading(sensor);

                                    put("temperature", sensorData.getTemperature());
                                    put("humidity", sensorData.getHumidity());
                                    put("timestamp", sensorData.getTimestamp());
                                }
                            });
                            case POWER_METER -> sensorDetails.put("data", new HashMap<>() {
                                {
                                    PowerMeterSensorData sensorData = sensorService.getPowerMeterSensorReading(sensor);
                                    put("current", sensorData.getCurrent());
                                    put("voltage", sensorData.getVoltage());
                                }
                            });
                            default -> throw new RuntimeException("Unknown sensor type");
                        }

                        add(sensorDetails);
                    });
                }
            });

            rooms.add(roomDetails);
        });

        return rooms;
    }



    @Override
    public Room createRoom(User user, NewRoomModel newRoomModel) {
        House house = user.getHouse();
        Long id = sequenceGenerator.generateSequence(Room.SEQ_NAME);
        Room room = new Room(id, newRoomModel.getName(), newRoomModel.getDescription(), house);

        return roomRepository.save(room);
    }

    @Override
    public Device addDeviceToRoom(User user, Long roomId, NewDeviceModel deviceModel) {
        checkUserHasRoom(user, roomId);

        // More device types will be added in the future
        switch (deviceModel.getType()) {
            case LIGHT:
                return addLightToRoom(user, roomId, deviceModel);
        }

        return null;
    }

    @Override
    public Sensor addSensorToRoom(User user, Long roomId, NewSensorModel sensorModel) {
        checkUserHasRoom(user, roomId);
        Room room = roomRepository.findById(roomId).orElseThrow();

        return sensorService.createSensor(
                sensorModel.getName(), sensorModel.getDescription(), sensorModel.getType(), room);
    }

    @Override
    public Light addLightToRoom(User user, Long roomId, NewDeviceModel deviceModel) {
        Room room = roomRepository.findById(roomId).orElseThrow();
        String name = deviceModel.getName();
        String description = Objects.requireNonNullElse(deviceModel.getDescription(), name + " On " + room.getName());

        return deviceService.createLight(name, description, room);
    }

    private void checkUserHasRoom(User user, Long roomId) {
        if (!userService.hasRoom(roomId, user)) {
            throw new RuntimeException("Room not found");
        }
    }
}
