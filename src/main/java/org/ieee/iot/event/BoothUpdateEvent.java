package org.ieee.iot.event;

import org.springframework.context.ApplicationEvent;

/*************************************************
 * Copyright (c) 2023-2-25 Abdullah Sayed Sallam.
 ************************************************/

public class BoothUpdateEvent extends ApplicationEvent {

    public BoothUpdateEvent(Object source) {
        super(source);
    }
}
