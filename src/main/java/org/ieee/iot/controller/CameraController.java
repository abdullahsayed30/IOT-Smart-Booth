package org.ieee.iot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ieee.iot.service.camera.CameraService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*************************************************
 * Copyright (c) 2023-2-26 Abdullah Sayed Sallam.
 ************************************************/
@RestController
@Slf4j
@RequestMapping("api/v1/camera")
@RequiredArgsConstructor
public class CameraController {

    private final CameraService cameraService;

    @GetMapping(produces = "image/jpeg")
    public byte[] getCameraImage() {

        byte[] image = cameraService
                .getLiveStream("http://45.245.215.33:60001/cgi-bin/snapshot.cgi?chn=0&u=admin&p=&q=0&1677431764");

        return image;
    }
}
