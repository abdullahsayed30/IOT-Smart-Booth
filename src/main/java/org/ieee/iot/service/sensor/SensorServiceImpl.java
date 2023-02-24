package org.ieee.iot.service.sensor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ieee.iot.domain.Room;
import org.ieee.iot.domain.User;
import org.ieee.iot.service.auth.AuthenticationFacade;
import org.ieee.iot.service.user.UserService;
import org.ieee.iot.utils.db.sequence.SequenceGenerator;
import org.ieee.iot.domain.Place;
import org.ieee.iot.domain.sensors.PowerMeterSensorData;
import org.ieee.iot.domain.sensors.Sensor;
import org.ieee.iot.domain.sensors.SensorType;
import org.ieee.iot.domain.sensors.TempHumSensorData;
import org.ieee.iot.repository.sensors.PowerConsSensorRepository;
import org.ieee.iot.repository.sensors.SensorRepository;
import org.ieee.iot.repository.sensors.TempHumSensorRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/*************************************************
 * Copyright (c) 2023-2-21 Abdullah Sayed Sallam.
 ************************************************/
@Service
@RequiredArgsConstructor
@Slf4j
public class SensorServiceImpl implements SensorService {
    private final AuthenticationFacade authenticationFacade;
    private final MongoTemplate template;
    private final SequenceGenerator sequenceGenerator;
    private final SensorRepository sensorRepository;
    private final TempHumSensorRepository tempHumSensorRepository;
    private final PowerConsSensorRepository powerConsSensorRepository;
    private final UserService userService;

    @Override
    public Sensor createSensor(String name, String description, SensorType type, Room room) {
        Long id = sequenceGenerator.generateSequence(Sensor.SEQ_NAME);
        Sensor sensor = new Sensor(id, name, description, type, room);
        return sensorRepository.save(sensor);
    }

    @Override
    public TempHumSensorData addTempHumSensorReading(Long sensorId, Integer temperature, Integer humidity) {
        Sensor sensor = sensorRepository.findById(sensorId).orElseThrow();

        if (!userHasPermissionOverSensor(sensor)) {
            return null;
        }

        Long id = sequenceGenerator.generateSequence(TempHumSensorData.SEQ_NAME);
        TempHumSensorData tempHumSensorData = new TempHumSensorData(id, sensor, temperature, humidity);
        return tempHumSensorRepository.save(tempHumSensorData);
    }

    @Override
    public TempHumSensorData getTempHumSensorReading(Sensor sensor) {
        return tempHumSensorRepository.findFirstBySensorOrderByTimestampDesc(sensor).orElse(new TempHumSensorData());
    }

    @Override
    public PowerMeterSensorData addPowerMeterSensorReading(Long sensorId, Integer current, Integer voltage) {
        Sensor sensor = sensorRepository.findById(sensorId).orElseThrow();

        if (!userHasPermissionOverSensor(sensor)) {
            return null;
        }

        Long id = sequenceGenerator.generateSequence(PowerMeterSensorData.SEQ_NAME);
        PowerMeterSensorData powerMeterSensorData = new PowerMeterSensorData(id, sensor, current, voltage);

        return powerConsSensorRepository.save(powerMeterSensorData);
    }

    @Override
    public PowerMeterSensorData getPowerMeterSensorReading(Sensor sensor) {
        return powerConsSensorRepository.findFirstBySensorOrderByTimestampDesc(sensor).orElse(new PowerMeterSensorData());
    }

    private boolean userHasPermissionOverSensor(Sensor sensor) {
        User user = authenticationFacade.getAuthenticatedUser();
        boolean hasPermission = userService.hasSensor(sensor, user);
        if (!hasPermission) {
            log.warn("User {} has no permission to access sensor {}", user.getUsername(), sensor.getId());
        }
        return hasPermission;
    }
}
