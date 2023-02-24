package org.ieee.iot.controller;

import lombok.RequiredArgsConstructor;
import org.ieee.iot.helper.res_model.Response;
import org.ieee.iot.service.device.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/*************************************************
 * Copyright (c) 2023-2-21 Abdullah Sayed Sallam.
 ************************************************/
@RequestMapping("/api/v1/light")
@RequiredArgsConstructor
@RestController
public class LightController {
    private final DeviceService deviceService;

    @PutMapping("/{lightId}/{state:on|off}")
    public ResponseEntity<Response> updateLightState(@PathVariable Long lightId, @PathVariable boolean state) {
        deviceService.updateLightState(lightId, state);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message("Light state updated successfully.")
                        .build()
        );
    }
}
