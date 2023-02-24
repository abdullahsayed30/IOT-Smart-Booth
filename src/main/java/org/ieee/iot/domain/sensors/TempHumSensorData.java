package org.ieee.iot.domain.sensors;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;


/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/

@Data
@ToString
@Document(collection = "TempHumSensorsData")
public class TempHumSensorData {

    public static final String SEQ_NAME = "TempHumSensorsData_sequence";

    public TempHumSensorData() {
    }

    public TempHumSensorData(Long id, Sensor sensor, Integer temperature, Integer humidity) {
        this.id = id;
        this.sensor = sensor;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    @Id
    private Long id;

    @DocumentReference
    private Sensor sensor;

    private Date timestamp = new Date();

    private Integer temperature;
    private Integer humidity;

}
