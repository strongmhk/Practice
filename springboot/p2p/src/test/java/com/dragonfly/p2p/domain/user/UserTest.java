package com.dragonfly.p2p.domain.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {


    @Test
    @DisplayName("유저 생성 테스트")
    void createMember(){
        // given
        User user = User.builder()
                .id(1L)
                .email("as")
                .name("김민형")
                .password("1234")
                .ssnPrefix("001114")
                .ssnSuffix("3249615")
                .phone("01065621660")
                .build();

        //when, then

        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getName()).isEqualTo("김민형");

    }

}