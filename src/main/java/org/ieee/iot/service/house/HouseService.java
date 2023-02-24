package org.ieee.iot.service.house;

import org.ieee.iot.domain.House;
import org.ieee.iot.domain.Room;
import org.ieee.iot.domain.User;

import java.util.List;


/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/

public interface HouseService {

    House createHouseForNewUser(User user);

    House createHouse(House house);

}
