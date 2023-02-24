package org.ieee.iot.service.device;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ieee.iot.domain.Room;
import org.ieee.iot.domain.User;
import org.ieee.iot.domain.devices.Device;
import org.ieee.iot.mqtt.IotTopics;
import org.ieee.iot.mqtt.MqttGateway;
import org.ieee.iot.service.auth.AuthenticationFacade;
import org.ieee.iot.service.user.UserService;
import org.ieee.iot.utils.db.sequence.SequenceGenerator;
import org.ieee.iot.domain.Place;
import org.ieee.iot.domain.devices.Light;
import org.ieee.iot.repository.devices.LightRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/*************************************************
 * Copyright (c) 2023-2-21 Abdullah Sayed Sallam.
 ************************************************/
@Service
@RequiredArgsConstructor
@Slf4j
public class DeviceServiceImpl implements DeviceService {

    private final AuthenticationFacade authenticationFacade;
    private final SequenceGenerator sequenceGenerator;
    private final MongoTemplate template;
    private final LightRepository lightRepository;
    private final UserService userService;
    private final MqttGateway mqttGateway;

    @Override
    public Light createLight(String name, String description, Room room) {
        Long id = sequenceGenerator.generateSequence(Light.SEQ_NAME);
        Light light = new Light(id, name, description, room);
        return lightRepository.save(light);
    }

    @Override
    public boolean updateLightState(Long lightId, boolean state) {
//        mqttGateway.sendToMqtt(IotTopics.LIGHT.getTopic() + "/" + lightId, String.valueOf(state));
        Light light = lightRepository.findById(lightId).orElse(new Light());
        if (userHasPermissionOverDevice(light)) {
            template.update(Light.class)
                    .matching(where("id").is(lightId))
                    .apply(new Update().set("state", state))
                    .first();
            return true;
        }
        return false;
    }

    private boolean userHasPermissionOverDevice(Device device) {
        User user = authenticationFacade.getAuthenticatedUser();
        return userService.hasDevice(device, user);
    }

}
