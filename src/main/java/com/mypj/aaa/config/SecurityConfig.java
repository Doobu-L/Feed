package com.mypj.aaa.config;

import com.mypj.aaa.jwt.JwtAccessDeniedHandler;
import com.mypj.aaa.jwt.JwtAuthenticationEntryPoint;
import com.mypj.aaa.jwt.JwtSecurityConfig;
import com.mypj.aaa.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security 5.7.8-M2 부터 구성요소 기반 보안 설정으로 변경됨에 따라 deprecated됨
 * 기존 configure 메소드 Override 하는 방식에서 SecurityFilterChain을 빈으로 등록하는 방식으로 변경
 * 참고 :: https://blog.naver.com/PostView.naver?blogId=h850415&logNo=222755455272&parentCategoryNo=&categoryNo=37&viewDate=&isShowPopularPosts=true&from=search
 *
 * Todo 변경된 방식으로 진행해보기
 *
 * @EnableGlobalMethodSecurity 추가 - @PreAuthorize 를 메소드 단위로 추가하기 위해 적용.
 * */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

  private final TokenProvider tokenProvider;
  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

  public SecurityConfig(
      TokenProvider tokenProvider,
      JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
      JwtAccessDeniedHandler jwtAccessDeniedHandler){
    this.tokenProvider = tokenProvider;
    this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;

  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web
        .ignoring()
        .antMatchers(
              "/favicon.ico"
        );
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        //custum exception handler
        .exceptionHandling()
        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
        .accessDeniedHandler(jwtAccessDeniedHandler)

        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        .and()
        .authorizeRequests()
        .antMatchers("/api/authenticate","/api/hello","/api/users/signup","/api/feeds/**","/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**","/api/schedules/**","swagger-ui/index.html").permitAll()   //권한허용
        .anyRequest().authenticated()
        .and()
        .apply(new JwtSecurityConfig(tokenProvider));


  }
}
