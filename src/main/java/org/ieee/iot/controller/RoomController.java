package org.ieee.iot.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ieee.iot.domain.User;
import org.ieee.iot.domain.devices.Device;
import org.ieee.iot.domain.devices.Light;
import org.ieee.iot.domain.sensors.Sensor;
import org.ieee.iot.helper.req_model.NewDeviceModel;
import org.ieee.iot.helper.req_model.NewRoomModel;
import org.ieee.iot.helper.req_model.NewSensorModel;
import org.ieee.iot.helper.res_model.Response;
import org.ieee.iot.service.auth.AuthenticationFacade;
import org.ieee.iot.service.house.HouseService;
import org.ieee.iot.service.room.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/

@RequestMapping("/api/v1/room")
@RequiredArgsConstructor
@RestController
public class RoomController {

    private final AuthenticationFacade authenticationFacade;
    private final RoomService roomService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<Response> getRooms() {
        User user = authenticationFacade.getAuthenticatedUser();
        return ResponseEntity.ok(Response.builder()
                .timeStamp(LocalDateTime.now().toString())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .message("Rooms retrieved successfully.")
                .data(Map.of("rooms", roomService.getRooms(user)))
                .build());
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<Response> createRoom(@RequestBody @Valid NewRoomModel newRoomModel) {
        User user = authenticationFacade.getAuthenticatedUser();

        return ResponseEntity.created(null)
                .body(Response.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Room created successfully.")
                        .data(Map.of("room", roomService.createRoom(user, newRoomModel)))
                        .build());
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{roomId}/device")
    public ResponseEntity<Response> addDeviceToRoom(@PathVariable Long roomId, @RequestBody @Valid NewDeviceModel deviceModel) {
        User user = authenticationFacade.getAuthenticatedUser();
        Device device = roomService.addDeviceToRoom(user, roomId, deviceModel);

        return ResponseEntity.created(null)
                .body(Response.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Device " + device.getName() + " added to room successfully.")
                        .build());
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{roomId}/sensor")
    public ResponseEntity<Response> addSensorToRoom(@PathVariable Long roomId, @RequestBody @Valid NewSensorModel newSensor) {
        User user = authenticationFacade.getAuthenticatedUser();
        Sensor sensor = roomService.addSensorToRoom(user, roomId, newSensor);

        return ResponseEntity.created(null)
                .body(Response.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Sensor " + sensor.getName() + " added to room successfully.")
                        .build());
    }
}
