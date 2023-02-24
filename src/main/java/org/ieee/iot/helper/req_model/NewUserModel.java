package org.ieee.iot.helper.req_model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;


/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/

@Data
public class NewUserModel {

    @NotEmpty(message = "First Name is required.")
    private String firstName;
    @NotEmpty(message = "Last Name is required.")
    private String lastName;
    @NotEmpty(message = "Address is required.")
    private String address;

    @Pattern(regexp = "[A-Za-z][A-Za-z0-9_]{7,29}$", message = "Username is Invalid.")
    private String username;

    @Email(message = "Email is Invalid.")
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "Password is Invalid.")
    private String password;

    @Indexed(unique = true)
    @NotEmpty(message = "Phone Number is required.")
    private String phoneNumber;

}
