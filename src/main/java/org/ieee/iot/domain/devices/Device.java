package org.ieee.iot.domain.devices;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.ieee.iot.domain.BaseDocument;
import org.ieee.iot.domain.Booth;
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
    private Booth booth;
    private String name;
    private String description;

    public Device(Long id, String name, String description, Booth booth) {
        super(id);
        this.name = name;
        this.description = description;
        this.booth = booth;
    }
}
