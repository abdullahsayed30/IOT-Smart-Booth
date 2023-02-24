package org.ieee.iot.config;

import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*************************************************
 * Copyright (c) 2023-2-24 Abdullah Sayed Sallam.
 ************************************************/
@Configuration
public class ObjectMapperConfig {
    @Bean
    public Gson gson() {
        return new GsonBuilder().create();
    }
}
