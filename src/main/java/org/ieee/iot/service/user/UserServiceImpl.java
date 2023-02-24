package org.ieee.iot.service.user;

import lombok.RequiredArgsConstructor;
import org.ieee.iot.domain.devices.Device;
import org.ieee.iot.domain.sensors.Sensor;
import org.ieee.iot.service.booth.BoothService;
import org.ieee.iot.utils.db.sequence.SequenceGenerator;
import org.ieee.iot.domain.User;
import org.ieee.iot.helper.req_model.NewUserModel;
import org.ieee.iot.repository.UserRepository;
import org.ieee.iot.service.token.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Map;


/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final SequenceGenerator sequenceGenerator;
    // Auth related
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final BoothService boothService;

    @Override
    public Map<String, String> signupNewUser(NewUserModel userModel) {
        if (userRepository.existsByUsername(userModel.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.existsByEmail(userModel.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Long id = sequenceGenerator.generateSequence(User.SEQ_NAME);


        User user = new User(
                id,
                userModel.getFirstName(),
                userModel.getLastName(),
                userModel.getAddress(),
                userModel.getUsername(),
                userModel.getEmail(),
                passwordEncoder.encode(userModel.getPassword()),
                userModel.getPhoneNumber()
        );

        user.setBooth(boothService.createBoothForNewUser(user));
        user.setRoles("USER");

        userRepository.save(user);

        return tokenService.generateTokens(user);
    }

    @Override
    public Map<String, String> loginUser(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return tokenService.generateTokens(user);
    }

    @Override
    public boolean hasDevice(Device device, User user) {
        return user.getBooth().equals(device.getBooth());
    }

    @Override
    public boolean hasSensor(Sensor sensor, User user) {
        return user.getBooth().equals(sensor.getBooth());
    }
}
