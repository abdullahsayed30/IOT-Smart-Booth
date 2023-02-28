package org.ieee.iot;

import org.ieee.iot.mqtt.MqttGateway;
import org.ieee.iot.service.camera.CameraService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;


/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/

@SpringBootApplication
@IntegrationComponentScan
public class IotCloudProjectApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(IotCloudProjectApplication.class, args);

        MqttGateway mqttGateway = (MqttGateway) context.getBean("mqttGateway");
        mqttGateway.senToMqtt("Hello from Spring Integration", "test");
    }

    @Bean
    public CommandLineRunner run(CameraService cameraService) {
        return args -> {
            System.out.println("Hello World");
            cameraService
                    .getLiveStream("http://41.38.112.180/SnapshotJPEG?Resolution=640x480&amp;Quality=Clarity&amp;1677401097");
        };
    }
}
