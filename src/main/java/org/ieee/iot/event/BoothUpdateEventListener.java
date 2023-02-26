package org.ieee.iot.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ieee.iot.domain.User;
import org.ieee.iot.service.auth.AuthenticationFacade;
import org.ieee.iot.service.booth.BoothService;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/*************************************************
 * Copyright (c) 2023-2-25 Abdullah Sayed Sallam.
 ************************************************/

@Component
@RequiredArgsConstructor
@Slf4j
public class BoothUpdateEventListener implements ApplicationListener<BoothUpdateEvent> {

    private final AuthenticationFacade authenticationFacade;
    private final SimpMessagingTemplate template;
    private final BoothService boothService;

    @Override
    public void onApplicationEvent(BoothUpdateEvent event) {
        log.info("BoothUpdateEventListener: {}", event);
        User user = authenticationFacade.getAuthenticatedUser();
        template.convertAndSendToUser(user.getUsername(), "/booth", boothService.getBoothDetails(user));
    }
}
