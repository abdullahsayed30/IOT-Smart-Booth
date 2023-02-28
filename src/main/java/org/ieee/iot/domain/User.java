package org.ieee.iot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
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
    @JsonIgnore
    private Booth booth;

    // Security related fields
    private boolean enabled = true;
    private String roles;

    public User(Long id, String firstName, String lastName, String address, String username, String email, String password, String phoneNumber, Booth booth) {
        super.setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.booth = booth;
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

    public boolean isAdmin(){
        return roles.contains("ADMIN");
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
