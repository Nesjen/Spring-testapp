package com.ensje;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by Nesjen on 05.02.2017.
 */
@Component
@Path("/hello")
public class TestEndpoint {

    @GET
    public String getHello()
    {
        return "Hello from JAX-RS running in Spring";
    }


}
