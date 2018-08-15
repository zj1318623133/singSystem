package com.example.smile.service.impl;

import com.example.smile.dao.UserMapper;
import com.example.smile.domain.User;
import com.example.smile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public String login(String userName, String password) {

        User user = userMapper.selectUserByName(userName);
        if (user == null){
            return "Not Found User ,Please Register,Thanks";
        }else {
            if (user.getUserName().equals(userName)){
                if (user.getPassword().equals(password)){
                    return "Login Success";
                }else {
                    return "Password Error,Please Input Correct Password";
                }
            }
        }
        return null;
    }

    @Override
    public String register(String userName, String password, String email) {
        return null;
    }
}
