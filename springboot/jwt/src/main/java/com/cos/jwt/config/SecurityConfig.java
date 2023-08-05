package com.cos.jwt.config;

import com.cos.jwt.config.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.addFilterBefore(new MyFilter3(), SecurityContextPersistenceFilter.class); // 시큐리티에 등록된 필터가 우리가 따로 만든 필터보다 먼저 실행됨
        // 따라서 Security에 등록돼있는 필터들보다 먼저 실행하려면, Before 사용
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 사용하지 않는 stateless 서버를 만들겠다.
                .and() // 서버마다 세션영역이 따로있어서, 서버를 여러 개 두는 경우에는 인증방식을 세션보다는 jwt를 이용하는게 좋다
                .addFilter(corsFilter) // @CrossOrigin(인증 X), 시큐리티 필터에 등록 인증(O)
                .formLogin().disable() // form 태그를 이용해 로그인 하지 않겠다.
                .httpBasic().disable() // http Basic(header에 Authorization : ID,PW 를 담아감 근데 암호화가 안돼서 보안에 취약) , http Bearer(header에 Authorization : token을 담아감 ID, PW 직접적 노출이 없어 보안 ㄱㅊ)
                .addFilter(new JwtAuthenticationFilter(authenticationManager())) // AuthenticationManager를 던져줘야함(WebSecurityConfigurerAdapter에 존재함)
                .authorizeRequests()
                .antMatchers("/api/v1/user/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/manager/**")
                .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                ;

    }
}
