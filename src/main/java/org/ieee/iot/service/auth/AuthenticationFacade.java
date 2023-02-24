package org.ieee.iot.service.auth;

import org.ieee.iot.domain.User;

/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/

public interface AuthenticationFacade {
    User getAuthenticatedUser();
}

