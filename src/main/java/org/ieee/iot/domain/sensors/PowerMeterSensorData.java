package org.ieee.iot.domain.sensors;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;


/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/

@ToString
@Data
@Document(collection = "PowerMeterSensorsData")
public class PowerMeterSensorData {

    public static final String SEQ_NAME = "PowerMeterSensorsData_sequence";

    public PowerMeterSensorData() {
    }

    public PowerMeterSensorData(Long id, Sensor sensor, Integer current, Integer voltage) {
        this.id = id;
        this.sensor = sensor;
        Current = current;
        Voltage = voltage;
    }

    @Id
    private Long id;

    @DocumentReference
    private Sensor sensor;

    private Date timestamp = new Date();

    private Integer Current;
    private Integer Voltage;

}
