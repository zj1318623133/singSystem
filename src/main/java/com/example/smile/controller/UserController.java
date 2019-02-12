package com.example.smile.controller;

import com.example.smile.service.UserService;
import com.example.smile.util.WebSecurityConfig;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
public class UserController {

    @Resource
    UserService userService;
    @RequestMapping(value = "home" ,method = RequestMethod.POST)
    public ModelAndView login( HttpServletRequest request){

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        System.out.println(userName + "--" + password);

        String result = userService.login(userName, password);
        System.out.println(result);
        if (result.contains("Login Success")){
            return new ModelAndView("home");
        }else {
            return null;
        }
    }

    @RequestMapping(value = "readingPlanHome",method = RequestMethod.POST)
    public ModelAndView ReadingPlanHome(HttpServletRequest request){

        ModelAndView modelAndView = new ModelAndView("crossHome");
        return modelAndView;
    }

    @RequestMapping(value = "singingPoetry",method = RequestMethod.POST)
    public ModelAndView SingingPoetryHome(HttpServletRequest request){

        ModelAndView modelAndView = new ModelAndView("singHome");
        return modelAndView;
    }

    @RequestMapping(value = "wonderfulMoment",method = RequestMethod.POST)
    public ModelAndView WonderfulMomentHome(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("wonderfulMomentHome");
        return modelAndView;
    }

    @RequestMapping(value = "myBlog",method = RequestMethod.POST)
    public ModelAndView MyBlogHome(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("MyBlogHome");
        return modelAndView;
    }
}
