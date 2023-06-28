package com.sprinkler.healthboard.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomOAuth2UserServiceImpl implements OAuth2UserService<OAuth2UserRequest, OAuth2User>, CustomOAuth2UserService {
    @Autowired  //스프링 컨테이너에 등록한 빈에게 의존관계주입이 필요할 때, DI(의존성 주입)을 도와주는 어노테이션
    private MemberRepository memberRepository; // Member 엔터티를 저장하고 검색하기 위한 Repository

    private final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String name = oAuth2User.getAttribute("name");
        String email = oAuth2User.getAttribute("email");


        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            member = Member.builder()
                    .nickname(name)
                    .email(email)
                    .role(Role.USER) // 기본 권한을 USER로 설정
                    .build();
            memberRepository.save(member);
        } else {
            // 이미 존재하는 Member의 정보를 업데이트한다면 이 부분에 로직을 작성해주세요.
            // 예를 들어, nickname이나 role이 변경되었을 때 업데이트하는 코드 등입니다.
        }

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(member.getRole().name())),
                Collections.singletonMap("email", member.getEmail()),
                "email");
    }
}
