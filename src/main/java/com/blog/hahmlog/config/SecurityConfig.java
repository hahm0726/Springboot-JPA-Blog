package com.blog.hahmlog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//빈 등록: 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것

@Configuration // 빈등록(IoC 관리)
@EnableWebSecurity // 시큐리티 필터가 등록이 된다 => 스프링 시큐리티가 활성화되어 있는데 어떤 설정을 해당 파일에서 하겠다
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근을 하면 권한 및 인증을 미리 체크한다는 뜻
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/auth/**") // /auth/가 붙은 모든 request에 대해
                .permitAll() //권한을 주고
                .anyRequest() //다른 모든 request에 대해
                .authenticated() //인증이 되어야한다
                .and()
                .formLogin()
                .loginPage("/auth/loginForm");

    }
}
