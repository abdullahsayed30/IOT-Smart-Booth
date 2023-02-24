package org.ieee.iot.repository;

import org.ieee.iot.domain.Room;
import org.springframework.data.mongodb.repository.MongoRepository;


/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/

public interface RoomRepository extends MongoRepository<Room, Long> {
}
