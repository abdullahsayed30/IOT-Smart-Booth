package org.ieee.iot.repository.sensors;

import org.ieee.iot.domain.sensors.Sensor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/*************************************************
 * Copyright (c) 2023-2-21 Abdullah Sayed Sallam.
 ************************************************/
@Repository
public interface SensorRepository extends MongoRepository<Sensor, Long> {
}
