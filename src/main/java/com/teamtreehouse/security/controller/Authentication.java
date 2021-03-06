package com.teamtreehouse.security.controller;

import com.teamtreehouse.security.transfer.AuthDTO;
import com.teamtreehouse.security.util.JwtToken;
import com.teamtreehouse.security.util.RestResponse;
import com.teamtreehouse.user.User;
import com.teamtreehouse.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by alex.andrade on 23/06/2017.
 */
@RestController
public class Authentication {

    private final UserRepository users;
    @Autowired
    private JwtToken jwtToken;

    @Autowired
    public Authentication(UserRepository users) {
        this.users = users;
    }

    @PostMapping(value = "/api/v1/public/auth")
    public ResponseEntity<?> auth(@RequestBody AuthDTO auth) {
        String userName = auth.getEmail();
        String passWord = auth.getPassword();

        /*TO DO: Verify user credentials here!*/
        User user = users.findByUsername(userName);

        if (user == null || !User.PASSWORD_ENCODER.matches(passWord, user.getPassword()))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        //JwtToken jwtToken = new JwtToken(users);

        RestResponse restResponse = new RestResponse(RestResponse.STATUS_SUCCESS, jwtToken.generateToken(user));

        return ResponseEntity.ok(restResponse);
    }
}
