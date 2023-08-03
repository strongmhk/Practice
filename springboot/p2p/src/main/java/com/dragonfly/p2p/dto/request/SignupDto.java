package com.dragonfly.p2p.dto.request;

import com.dragonfly.p2p.domain.user.Telecom;
import com.dragonfly.p2p.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignupDto {

    @NotBlank
    private String email;

    @Size(min = 2, max = 10)
    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @NotBlank
    private String ssnPrefix;

    @NotBlank
    private String ssnSuffix;


    private Telecom telecom;

    @NotBlank
    private String phone;


    public User toEntity(){
        return User.builder()
                .email(email)
                .name(name)
                .password(password)
                .ssnPrefix(ssnPrefix)
                .ssnSuffix(ssnSuffix)
                .telecom(telecom)
                .phone(phone)
                .build();
    }


}
