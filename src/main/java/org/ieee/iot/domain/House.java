package org.ieee.iot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.ieee.iot.domain.devices.Device;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;


/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("houses")
@JsonIgnoreProperties({"id", "owner"})
public class House extends BaseDocument{
    public final static String SEQ_NAME = "houses_seq";

    private String name;
    private String description;
    private String address;

    @DocumentReference
    private User owner;

    @ReadOnlyProperty
    @DocumentReference(lookup = "{ 'house': ?#{#self._id} }")
    private List<Room> rooms;

    public House(Long id, String name, String description, String address, User owner) {
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
                ", rooms=" + rooms +
                '}';
    }
}
