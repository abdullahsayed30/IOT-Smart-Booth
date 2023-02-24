package org.ieee.iot.service.booth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ieee.iot.utils.db.sequence.SequenceGenerator;
import org.ieee.iot.domain.Booth;
import org.ieee.iot.domain.User;
import org.ieee.iot.domain.devices.Device;
import org.ieee.iot.domain.devices.Light;
import org.ieee.iot.domain.sensors.PowerMeterSensorData;
import org.ieee.iot.domain.sensors.Sensor;
import org.ieee.iot.domain.sensors.SensorType;
import org.ieee.iot.domain.sensors.TempHumSensorData;
import org.ieee.iot.helper.req_model.NewDeviceModel;
import org.ieee.iot.helper.req_model.NewSensorModel;
import org.ieee.iot.repository.BoothRepository;
import org.ieee.iot.service.device.DeviceService;
import org.ieee.iot.service.sensor.SensorService;
import org.springframework.stereotype.Service;

import java.util.*;


/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/

@Service
@RequiredArgsConstructor
@Slf4j
public class BoothServiceImpl implements BoothService {
    private final SequenceGenerator sequenceGenerator;
    private final BoothRepository boothRepository;
    private final DeviceService deviceService;
    private final SensorService sensorService;


    @Override
    public Map<String, Object> getBoothDetails(User user) {

        Booth booth = user.getBooth();
        Map<String, Object> boothDetails = new LinkedHashMap<>();

        boothDetails.put("name", booth.getName());
        boothDetails.put("description", booth.getDescription());
        boothDetails.put("lights", booth.getLights());
        boothDetails.put("sensors", new ArrayList<>() {
            {
                booth.getSensors().forEach(sensor -> {
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
        return boothDetails;
}


    @Override
    public Booth createBoothForNewUser(User user) {
        Long id = sequenceGenerator.generateSequence(Booth.SEQ_NAME);
        Booth room = new Booth(id, user.getFullName() + "'s Booth",
                user.getFullName() + "'s Booth",
                user.getAddress(), user);

        return boothRepository.save(room);
    }

    @Override
    public Device addDeviceToBooth(User user, NewDeviceModel deviceModel) {
        // More device types will be added in the future
        switch (deviceModel.getType()) {
            case LIGHT:
                return addLightToBooth(user, deviceModel);
        }

        return null;
    }

    @Override
    public Sensor addSensorToBooth(User user, NewSensorModel sensorModel) {
        Booth booth = user.getBooth();

        return sensorService.createSensor(
                sensorModel.getName(), sensorModel.getDescription(), sensorModel.getType(), booth);
    }

    @Override
    public Light addLightToBooth(User user, NewDeviceModel deviceModel) {
        Booth booth = user.getBooth();
        String name = deviceModel.getName();
        String description =
                Objects.requireNonNullElse(deviceModel.getDescription(), name + " On " + booth.getName());

        return deviceService.createLight(name, description, booth);
    }

}
