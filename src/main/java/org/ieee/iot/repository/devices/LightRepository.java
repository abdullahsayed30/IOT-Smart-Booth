package org.ieee.iot.repository.devices;

import org.ieee.iot.domain.devices.Light;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/

@Repository
public interface LightRepository extends MongoRepository<Light, Long> {

}
