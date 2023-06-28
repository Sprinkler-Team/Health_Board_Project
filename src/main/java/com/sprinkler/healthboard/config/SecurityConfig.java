package com.sprinkler.healthboard.config;

import com.sprinkler.healthboard.member.CustomOAuth2UserService;
import com.sprinkler.healthboard.member.CustomOAuth2UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomOAuth2UserServiceImpl customOAuth2UserServiceImpl;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()   //CSRF(Cross-Site Request Forgery) 공격을 방지하는 설정입니다. 이를 비활성화
                .headers().frameOptions().disable() //웹 페이지가 <frame>, <iframe>, <embed> 태그로 다른 페이지를 삽입하는 것을 방지하는 설정입니다. 이 또한 비활성화
                .and()
                .authorizeRequests()    //HttpServletRequest에 대한 접근 제한을 설정하는 옵션
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()  //특정 경로에 대한 요청은 인증을 필요로 하지 않도록 허용하는 설정
                    .anyRequest().authenticated()   //그외  모든 요청 권한필요
                .and()
                .logout().logoutSuccessUrl("/") //로그아웃 요청이 성공했을 경우, 리다이렉션 될 URL을 설정
                .and()
                .oauth2Login()
                    .defaultSuccessUrl("/loginSuccess")  //로그인에 성공했을 경우, 리다이렉션 될 URL을 설정
                    .failureUrl("/loginFailure") //로그인에 실패했을 경우, 리다이렉션 될 URL을 설정
                .and()
                .oauth2Login()
                    .userInfoEndpoint()
                        .userService(customOAuth2UserServiceImpl);  //OAuth 2.0 로그인 성공 후, 사용자 정보를 가져올 때의 설정입니다. 사용자 정보를 가져오는 서비스로 customOAuth2UserService를 사용



        return http.build();


    }
}
