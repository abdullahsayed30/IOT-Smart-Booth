package org.ieee.iot.domain.sensors;

import lombok.*;
import org.ieee.iot.domain.BaseDocument;
import org.ieee.iot.domain.Place;
import org.ieee.iot.domain.Room;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;


/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/
@Getter
@Setter
@ToString
@Document("sensors")
public class Sensor extends BaseDocument {

    public final static String SEQ_NAME = "sensors_sequence";
    @DocumentReference
    private Room room;
    private String name;
    private String description;
    private SensorType type;

    public Sensor(Long id, String name, String description, SensorType type, Room room) {
        super(id);
        this.name = name;
        this.description = description;
        this.type = type;
        this.room = room;
    }
}
