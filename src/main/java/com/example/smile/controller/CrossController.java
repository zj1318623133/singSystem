package com.example.smile.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@RestController
@Configuration
@RequestMapping(value = "/cross")
public class CrossController {

    @RequestMapping(value = "readingPlanHome",method = RequestMethod.POST)
    public ModelAndView ReadingPlanHome(HttpServletRequest request, HttpServletResponse response, Model model){

        ModelAndView modelAndView = new ModelAndView("/crossHome");
        return modelAndView;
    }
}
