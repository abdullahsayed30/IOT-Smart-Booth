package org.ieee.iot.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.ieee.iot.domain.devices.Light;
import org.ieee.iot.domain.sensors.Sensor;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import java.util.List;


/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("booths")
@JsonIgnoreProperties({"id", "owner"})
public class Booth extends BaseDocument{
    public final static String SEQ_NAME = "booths_seq";

    private String name;
    private String description;
    private String address;

    @DocumentReference
    private User owner;

    @ReadOnlyProperty
    @DocumentReference(lookup = "{ 'booth': ?#{#self._id} }")
    private List<Light> lights;

    @ReadOnlyProperty
    @DocumentReference(lookup = "{ 'booth': ?#{#self._id} }")
    private List<Sensor> sensors;

    public Booth(Long id, String name, String description, String address, User owner) {
        super.setId(id);
        this.name = name;
        this.description = description;
        this.address = address;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "House{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
