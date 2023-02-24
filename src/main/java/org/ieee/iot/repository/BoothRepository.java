package org.ieee.iot.repository;

import org.ieee.iot.domain.Booth;
import org.springframework.data.mongodb.repository.MongoRepository;


/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/

public interface BoothRepository extends MongoRepository<Booth, Long> {
}
