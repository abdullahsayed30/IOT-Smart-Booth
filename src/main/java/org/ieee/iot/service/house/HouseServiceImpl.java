package org.ieee.iot.service.house;

import lombok.RequiredArgsConstructor;
import org.ieee.iot.utils.db.sequence.SequenceGenerator;
import org.ieee.iot.domain.House;
import org.ieee.iot.domain.User;
import org.ieee.iot.repository.HouseRepository;
import org.springframework.stereotype.Service;


/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;
    private final SequenceGenerator sequenceGenerator;

    @Override
    public House createHouse(House house) {
        return houseRepository.save(house);
    }

    @Override
    public House createHouseForNewUser(User user) {
        Long houseId = sequenceGenerator.generateSequence(House.SEQ_NAME);
        House house = new House(
                houseId,
                user.getFullName() + "'s House",
                user.getFullName() + "'s house where they live",
                user.getAddress(),
                user
        );

        return houseRepository.save(house);
    }

}
