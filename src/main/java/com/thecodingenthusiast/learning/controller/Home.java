package com.thecodingenthusiast.learning.controller;

import com.thecodingenthusiast.learning.model.UserDetail;
import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/hello")
public class Home {
    private static final Logger LOG = Logger.getLogger(Home.class);

    @GET
    public String getMsg() {
        UserDetail user = UserDetail.builder().firstName("Ankit").lastName("Agrawal").build();
        LOG.info("User: " + user);
        return "Hello World !! - Jersey";
    }
}
