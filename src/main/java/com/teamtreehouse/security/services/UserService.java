package com.teamtreehouse.security.services;

import com.teamtreehouse.user.User;
import com.teamtreehouse.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{

    private final UserRepository users;

    @Autowired
    public UserService(UserRepository users) {
        this.users = users;
    }

    public Boolean authenticate(String userName, String passWord){
        User user = users.findByUsername(userName);
        if (null!=user){
            //return user.getPassWord().equals(passWord);
            return true;
        }
        return false;
    }
}
