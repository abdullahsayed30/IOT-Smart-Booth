package org.ieee.iot.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ieee.iot.domain.User;
import org.ieee.iot.domain.devices.Device;
import org.ieee.iot.domain.sensors.Sensor;
import org.ieee.iot.helper.req_model.NewDeviceModel;
import org.ieee.iot.helper.req_model.NewSensorModel;
import org.ieee.iot.helper.res_model.Response;
import org.ieee.iot.service.auth.AuthenticationFacade;
import org.ieee.iot.service.booth.BoothService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Map;

/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/

@RequestMapping("/api/v1/booth")
@RequiredArgsConstructor
@RestController
public class BoothController {

    private final AuthenticationFacade authenticationFacade;
    private final BoothService boothService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<Response> getBooth() {
        User user = authenticationFacade.getAuthenticatedUser();
        return ResponseEntity.ok(Response.builder()
                .timeStamp(LocalDateTime.now().toString())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .message("Rooms retrieved successfully.")
                .data(Map.of("rooms", boothService.getBoothDetails(user)))
                .build());
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/device")
    public ResponseEntity<Response> addDeviceToRoom(@RequestBody @Valid NewDeviceModel deviceModel) {
        User user = authenticationFacade.getAuthenticatedUser();
        Device device = boothService.addDeviceToBooth(user, deviceModel);

        return ResponseEntity.created(null)
                .body(Response.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Device " + device.getName() + " added to booth successfully.")
                        .build());
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/sensor")
    public ResponseEntity<Response> addSensorToRoom(@RequestBody @Valid NewSensorModel newSensor) {
        User user = authenticationFacade.getAuthenticatedUser();
        Sensor sensor = boothService.addSensorToBooth(user, newSensor);

        return ResponseEntity.created(null)
                .body(Response.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Sensor " + sensor.getName() + " added to booth successfully.")
                        .build());
    }
}
