package com.example.smile.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

    String login(String userNmae,String password);

    String register (String userName,String password,String email);
}
