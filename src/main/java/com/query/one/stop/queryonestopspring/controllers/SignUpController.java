package com.query.one.stop.queryonestopspring.controllers;

import com.query.one.stop.queryonestopspring.dto.SignUpDto;
import com.query.one.stop.queryonestopspring.dto.UserDTO;
import com.query.one.stop.queryonestopspring.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody SignUpDto signUpDto){

        if(userService.hasUserWithEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("User already exist with this email",HttpStatus.NOT_ACCEPTABLE);
        }

        UserDTO createdUser = userService.createUser(signUpDto);
        if(createdUser==null){
            return new ResponseEntity<>("User not created, please try again later !!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdUser,HttpStatus.CREATED); // we send user as well as the status of the operation

    }

}
