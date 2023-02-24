package org.ieee.iot.service.sensor;

import org.ieee.iot.domain.Place;
import org.ieee.iot.domain.Room;
import org.ieee.iot.domain.sensors.PowerMeterSensorData;
import org.ieee.iot.domain.sensors.Sensor;
import org.ieee.iot.domain.sensors.SensorType;
import org.ieee.iot.domain.sensors.TempHumSensorData;

/*************************************************
 * Copyright (c) 2023-2-19 Abdullah Sayed Sallam.
 ************************************************/

public interface SensorService {
    Sensor createSensor(String name, String description, SensorType type, Room room);

    TempHumSensorData addTempHumSensorReading(Long sensorId, Integer temperature, Integer humidity);

    TempHumSensorData getTempHumSensorReading(Sensor sensor);

    PowerMeterSensorData addPowerMeterSensorReading(Long sensorId, Integer current, Integer voltage);
    PowerMeterSensorData getPowerMeterSensorReading(Sensor sensor);

}
