package org.ieee.iot.mqtt;

import com.nimbusds.jose.shaded.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ieee.iot.domain.User;
import org.ieee.iot.domain.sensors.PowerMeterSensorData;
import org.ieee.iot.domain.sensors.TempHumSensorData;
import org.ieee.iot.helper.req_model.mqtt.BaseMqttModel;
import org.ieee.iot.helper.req_model.mqtt.devices.LightUpdateModel;
import org.ieee.iot.helper.req_model.mqtt.sensors.PowerMeterDataModel;
import org.ieee.iot.helper.req_model.mqtt.sensors.TmpHumDataModel;
import org.ieee.iot.repository.UserRepository;
import org.ieee.iot.service.device.DeviceService;
import org.ieee.iot.service.sensor.SensorService;
import org.ieee.iot.service.token.TokenService;
import org.ieee.iot.service.user.UserService;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*************************************************
 * Copyright (c) 2023-2-22 Abdullah Sayed Sallam.
 ************************************************/
@Slf4j
@Service
@RequiredArgsConstructor
public class MqttMessageReceiver implements MessageHandler {
    private static final String TOPIC_HEADER = "mqtt_receivedTopic";
    private final Gson gson;
    private final DeviceService deviceService;
    private final SensorService sensorService;
    private final TokenService tokenService;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        String topic = Optional.ofNullable((String) message.getHeaders().get(TOPIC_HEADER)).orElse("THAT WILL NEVER HAPPEN");
        String payload = (String) message.getPayload();

        BaseMqttModel baseModel = gson.fromJson(payload, BaseMqttModel.class);


        // Set the authentication context for the current user
        SecurityContextHolder.getContext().setAuthentication(tokenService.getAuthentication(baseModel.getToken()));

        if (topic.matches(IotTopics.LIGHT.getTopic())) {
            LightUpdateModel model = gson.fromJson(payload, LightUpdateModel.class);

            boolean updated = deviceService.updateLightState(model.getLightId(), model.isState());

            if (updated)
                log.info("Light with ID " + model.getLightId() + " updated to " + (model.isState() ? "ON" : "OFF"));
        }

        else if (topic.matches(IotTopics.TEMP_HUM.getTopic())) {
            // Temp #BadCode, Convert to a better way for validating the payload
            if(!payload.contains("temperature")) {
                return;
            }

            TmpHumDataModel model = gson.fromJson(payload, TmpHumDataModel.class);
            TempHumSensorData tempHumSensorData = sensorService.addTempHumSensorReading(model.getSensorId(), model.getTemperature(), model.getHumidity());

            if (tempHumSensorData != null)
                log.info("Temp_Hum Data added: " + model.getSensorId() + " " + model.getTemperature() + " " + model.getHumidity());
        }

        else if (topic.matches(IotTopics.POWER_METER.getTopic())) {
            // Temp #BadCode, Convert to a better way for validating the payload
            if(!payload.contains("current")) {
                return;
            }

            PowerMeterDataModel model = gson.fromJson(payload, PowerMeterDataModel.class);
            PowerMeterSensorData powerMeterSensorData = sensorService.addPowerMeterSensorReading(model.getSensorId(), model.getCurrent(), model.getVoltage());
            if (powerMeterSensorData != null)
                log.info("Power_Meter: " + model.getSensorId() + " " + model.getCurrent() + " " + model.getVoltage());
        }

        else {
            log.info("Unknown topic: {}", topic);
        }

    }
}
