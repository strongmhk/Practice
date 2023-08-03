package com.example.instaclone.controller;

import com.example.instaclone.dto.auth.request.SignUpReqDto;
import com.example.instaclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    public void registerUser(@RequestBody SignUpReqDto signUpReqDto){
//        userService.
    }


}
