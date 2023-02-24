package org.ieee.iot.service.auth;

import lombok.RequiredArgsConstructor;
import org.ieee.iot.domain.User;
import org.ieee.iot.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;


/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/
@Service
@RequiredArgsConstructor
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    private final UserRepository userRepository;

    @Override
    public User getAuthenticatedUser() {
        return userRepository.findByUsername(
                        ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getSubject())
                .orElseThrow();
    }
}
