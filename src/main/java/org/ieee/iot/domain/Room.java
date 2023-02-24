package org.ieee.iot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.ieee.iot.domain.devices.Device;
import org.ieee.iot.domain.devices.Light;
import org.ieee.iot.domain.sensors.Sensor;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;


/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/

@Getter
@Setter
@Document("rooms")
public class Room extends Place {

    @Transient
    public static final String SEQ_NAME = "rooms_sequence";

    private String name;
    private String description;

    @DocumentReference
    @JsonIgnore
    private House house;

    @ReadOnlyProperty
    @DocumentReference(lookup = "{ 'room': ?#{#self._id} }")
    private List<Light> lights;

    @ReadOnlyProperty
    @DocumentReference(lookup = "{ 'room': ?#{#self._id} }")
    private List<Sensor> sensors;

    public Room(Long id, String name, String description, House house) {
        super.setId(id);
        this.name = name;
        this.description = description;
        this.house = house;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", lights=" + lights +
                ", sensors=" + sensors +
                '}';
    }
}
