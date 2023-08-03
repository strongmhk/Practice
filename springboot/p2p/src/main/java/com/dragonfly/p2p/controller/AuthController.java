package com.dragonfly.p2p.controller;

import com.dragonfly.p2p.domain.user.User;
import com.dragonfly.p2p.dto.CMRespDto;
import com.dragonfly.p2p.dto.request.SigninDto;
import com.dragonfly.p2p.dto.request.SignupDto;
import com.dragonfly.p2p.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;
    private final Logger log = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/auth/signin")
    public ResponseEntity<?> signin(SigninDto signinDto){
        authService.signin();
        return null;
    }




    @PostMapping("/auth/signup")
    public ResponseEntity<?> signup(@Valid SignupDto signupDto, BindingResult bindingResult){ // key=value (x-www-form-urlencoded)

        if(bindingResult.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
                System.out.println("============================");
                System.out.println(error.getDefaultMessage());
                System.out.println("============================");
            }

//            throw new CustomValidationException("유효성검사 실패함", errorMap);
            throw new IllegalArgumentException();
        }else {
            log.info(signupDto.toString());
            User user = signupDto.toEntity();
            log.info(user.toString());
            User userEntity = authService.signup(user);
            System.out.println(userEntity);
            return new ResponseEntity<>(new CMRespDto<>(1, "회원가입 성공",  userEntity), HttpStatus.CREATED);
        }


    }
}
