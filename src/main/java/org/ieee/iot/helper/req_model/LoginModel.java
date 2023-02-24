package org.ieee.iot.helper.req_model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/*************************************************
 * Copyright (c) 2023-2-22 Abdullah Sayed Sallam.
 ************************************************/
@Data
public class LoginModel {
    @NotEmpty(message = "Username is required")
    private String username;
    @NotEmpty(message = "Password is required")
    private String password;
}
