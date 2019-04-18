package com.thecodingenthusiast.learning.controller;

import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/hello")
public class Home {
    private static final Logger LOG = Logger.getLogger(Home.class);

    @GET
    public String getMsg() {
        return "Hello World !! - Jersey 2.28";
    }
}
