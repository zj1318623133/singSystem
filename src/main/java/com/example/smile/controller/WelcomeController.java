package com.example.smile.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Configuration
@RequestMapping(value = "/")
public class WelcomeController {

    public ModelAndView index(){
        return new ModelAndView("index");
    }
}
