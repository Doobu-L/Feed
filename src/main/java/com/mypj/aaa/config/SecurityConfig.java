package com.mypj.aaa.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Security 5.7.8-M2 부터 구성요소 기반 보안 설정으로 변경됨에 따라 deprecated됨
 * 기존 configure 메소드 Override 하는 방식에서 SecurityFilterChain을 빈으로 등록하는 방식으로 변경
 * 참고 :: https://blog.naver.com/PostView.naver?blogId=h850415&logNo=222755455272&parentCategoryNo=&categoryNo=37&viewDate=&isShowPopularPosts=true&from=search
 *
 * Todo 변경된 방식으로 진행해보기
 * */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/api").permitAll()   //권한허용
        .anyRequest().authenticated(); //인증필요
  }
}
