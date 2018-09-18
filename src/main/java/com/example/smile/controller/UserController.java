package com.example.smile.controller;

import com.example.smile.service.UserService;
import com.example.smile.util.WebSecurityConfig;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@RestController
@RequestMapping("/")
public class UserController {

    @Resource
    UserService userService;
//@SessionAttribute(WebSecurityConfig.SESSION_KEY) String account,
    @RequestMapping(value = "home",method = RequestMethod.POST)
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


}
