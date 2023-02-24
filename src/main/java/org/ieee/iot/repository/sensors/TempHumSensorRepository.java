package org.ieee.iot.repository.sensors;

import org.ieee.iot.domain.sensors.Sensor;
import org.ieee.iot.domain.sensors.TempHumSensorData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/
@Repository
public interface TempHumSensorRepository extends MongoRepository<TempHumSensorData, Long> {
    Optional<TempHumSensorData> findFirstBySensorOrderByTimestampDesc(Sensor sensor);
}
