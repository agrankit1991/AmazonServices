package com.thecodingenthusiast.learning.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Home {
    private static final Logger LOG = Logger.getLogger(Home.class);

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ModelAndView get() {
        LOG.info("Hello there..");
        return new ModelAndView("hello", "user", "Ankit");
    }
}
