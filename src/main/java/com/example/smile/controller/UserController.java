package com.example.smile.controller;

import com.example.smile.service.UserService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping("/login")
    public String login(HttpServletRequest request){

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        System.out.println(userName + "--" + password);

        String result = userService.login(userName, password);
        System.out.println(result);
        if (result.contains("Login Success")){
            return "home";
        }else {
            return null;
        }
    }


}
