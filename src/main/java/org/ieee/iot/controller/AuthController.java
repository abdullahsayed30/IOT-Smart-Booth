package org.ieee.iot.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.ieee.iot.domain.User;
import org.ieee.iot.helper.req_model.LoginModel;
import org.ieee.iot.helper.req_model.NewUserModel;
import org.ieee.iot.helper.res_model.Response;
import org.ieee.iot.service.token.TokenService;
import org.ieee.iot.service.user.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;


/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserServiceImpl authServiceImpl;
    private final TokenService tokenService;

    @PostMapping("/signup")
    public ResponseEntity<Response> signup(@RequestBody @Valid NewUserModel newUserModel) {
        Map<String, String> tokens = authServiceImpl.signupNewUser(newUserModel);

        return ResponseEntity.created(null)
                .body(Response.builder()
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .timeStamp(LocalDateTime.now().toString())
                        .message("User created successfully.")
                        .data(tokens)
                        .build()
                );
    }


    @PostMapping("/login")
    public ResponseEntity<Response> login(@Valid @RequestBody LoginModel loginModel) {
        Map<String, String> tokens = authServiceImpl.loginUser(loginModel.getUsername(), loginModel.getPassword());

        return ResponseEntity.ok(
                Response.builder()
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .timeStamp(LocalDateTime.now().toString())
                        .message("User logged in successfully.")
                        .data(tokens)
                        .build()
        );
    }

    @PostMapping("/refresh")
    public ResponseEntity<Response> refreshToken(HttpServletRequest request, @AuthenticationPrincipal Jwt refreshToken) {

        if (request.getHeader("Authorization") == null) {
            return ResponseEntity.badRequest()
                    .body(Response.builder()
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .timeStamp(LocalDateTime.now().toString())
                            .message("Invalid request musts include token on Authorization header.")
                            .build()
                    );
        }

        String token = tokenService.generateToken(tokenService.getUser(refreshToken));

        return ResponseEntity.ok(
                Response.builder()
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .timeStamp(LocalDateTime.now().toString())
                        .message("Token refreshed successfully.")
                        .data(
                                Map.of(
                                        "accessToken", token,
                                        "refreshToken", refreshToken.getTokenValue()
                                )
                        )
                        .build()
        );
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/@me")
    public ResponseEntity<Response> getUser(@AuthenticationPrincipal Jwt jwt) {
        User user = tokenService.getUser(jwt);

        return ResponseEntity.ok(
                Response.builder()
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .timeStamp(LocalDateTime.now().toString())
                        .message("User details fetched successfully.")
                        .data(Map.of("user", user))
                        .build()
        );
    }

}