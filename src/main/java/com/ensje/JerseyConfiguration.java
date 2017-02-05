package com.ensje;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

/**
 * Created by Nesjen on 05.02.2017.
 */
@Component
@ApplicationPath("/api")
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration() {
        register(TestEndpoint.class);
    }

}
