package org.ieee.iot.domain.devices;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.ieee.iot.domain.BaseDocument;
import org.ieee.iot.domain.House;
import org.ieee.iot.domain.Room;
import org.springframework.data.mongodb.core.mapping.DocumentReference;


/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Device extends BaseDocument {

    @DocumentReference
    @JsonIgnore
    private Room room;
    private String name;
    private String description;

    public Device(Long id, String name, String description, Room room) {
        super(id);
        this.name = name;
        this.description = description;
        this.room = room;
    }
}
