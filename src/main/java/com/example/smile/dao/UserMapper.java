package com.example.smile.dao;

import com.example.smile.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    User selectUserByName (String userName);

    User register (String userName,String password,String email);
}
