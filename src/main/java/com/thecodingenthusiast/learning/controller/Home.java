package com.thecodingenthusiast.learning.controller;

import com.thecodingenthusiast.learning.model.UserDetail;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Slf4j
@Path("/hello")
public class Home {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserDetail getMsg() {
        UserDetail user = UserDetail.builder().firstName("Ankit").lastName("Agrawal").build();
        log.info("User: " + user);
        return user;
    }
}
