package org.ieee.iot.repository;

import org.ieee.iot.domain.House;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/

@Repository
public interface HouseRepository extends MongoRepository<House, Long> {


}
