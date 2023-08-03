package com.dragonfly.p2p.service;

import com.dragonfly.p2p.domain.user.Role;
import com.dragonfly.p2p.domain.user.User;
import com.dragonfly.p2p.domain.user.UserRepository;
import com.dragonfly.p2p.handler.ex.ApiReqException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(readOnly = true)
    public void signin(){

    }



    @Transactional
    public User signup(User user){
        if(userRepository.existsByEmail(user.getEmail())){
            throw new ApiReqException("이미 존재하는 이메일입니다.");
        }

        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole(Role.valueOf("USER"));
        User userEntity = userRepository.save(user);

        return userEntity;
    }

}
