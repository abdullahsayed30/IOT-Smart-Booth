package org.ieee.iot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;


/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/

@Getter
@Setter
@Document("users")
@NoArgsConstructor
@JsonIgnoreProperties({"id", "password", "enabled"})
public class User extends BaseDocument{

    @Transient
    public static final String SEQ_NAME = "users_sequence";
    private String firstName;
    private String lastName;
    private String address;
    @Indexed(unique = true)
    private String username;
    @Indexed(unique = true)
    private String email;
    // Stores the encoded password, The real password is validated by another class.
    private String password;
    @Indexed(unique = true)
    private String phoneNumber;

    @DocumentReference
    private House house;

    // Security related fields
    private boolean enabled = true;
    private String roles;

    public User(Long id, String firstName, String lastName, String address, String username, String email, String password, String phoneNumber, House house) {
        super.setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.house = house;
    }

    public User(Long id, String firstName, String lastName, String address, String username, String email, String password, String phoneNumber) {
        super.setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }
}
