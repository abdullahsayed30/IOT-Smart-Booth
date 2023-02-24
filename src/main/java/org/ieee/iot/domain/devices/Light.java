package org.ieee.iot.domain.devices;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.ieee.iot.domain.Place;
import org.ieee.iot.domain.Room;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import java.util.Date;


/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/

@Getter
@Setter
@ToString
@JsonIgnoreProperties({"room", "version", "createdAt", "updatedAt"})
@Document("lights")
public class Light extends Device {

    @Transient
    public final static String SEQ_NAME = "lights_sequence";

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    @Version
    private Long version;

    private boolean state;

    public Light(Long id, String name, String description, Room room) {
        super(id, name, description, room);
    }

    public Light() {
    }
}
