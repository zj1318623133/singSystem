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
        System.out.println("进来了么");
        return new ModelAndView("index");
    }
}
