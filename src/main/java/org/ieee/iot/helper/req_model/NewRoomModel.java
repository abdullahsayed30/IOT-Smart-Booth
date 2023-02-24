package org.ieee.iot.helper.req_model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/

@Data
public class NewRoomModel {
    @NotEmpty(message = "Room name is required")
    private String name;
    private String description;
}
