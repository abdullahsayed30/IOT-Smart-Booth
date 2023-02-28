package org.ieee.iot.service.camera;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.util.Collections;
import java.util.Objects;


/*************************************************
 * Copyright (c) 2023-2-26 Abdullah Sayed Sallam.
 ************************************************/
@Slf4j
@Service
@RequiredArgsConstructor
public class CameraServiceImp implements CameraService {

    private ResourceLoader resourceLoader;

    @Override
    public byte[] getLiveStream(String url) {
        RestTemplate temp = new RestTemplateBuilder().build();


        ResponseEntity<byte[]> responseEntity = temp.getForEntity(
                url,
                byte[].class);


        return Objects.requireNonNull(responseEntity.getBody());
    }
}
