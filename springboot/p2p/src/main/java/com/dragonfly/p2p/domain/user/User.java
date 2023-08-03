package com.dragonfly.p2p.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDateTime;



@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 고유값

    @Column(unique = true, nullable = false)
    private String email; // 이메일 -> 로그인할 때 필요한 정보

    @Column( nullable = false)
    private String password;

    @Column(nullable = false)
    private String name; // 실명

    @Column(nullable = false)
    private String phone;

    @Column(name = "ssn_prefix", nullable = false)
    private String ssnPrefix; // 주민번호 앞자리

    @Column(name = "ssn_suffix", nullable = false)
    private String ssnSuffix; // 주민번호 뒷자리

    @Enumerated(EnumType.STRING)
    private Telecom telecom;

    @Column(name = "easy_pwd", length = 6)
    private String easyPwd;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime createDate; // 데이터가 언제들어왔는지

    @PrePersist // 디비에 insert 되기 직전에 실행
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }


}
