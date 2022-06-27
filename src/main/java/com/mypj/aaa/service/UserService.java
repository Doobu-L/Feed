package com.mypj.aaa.service;

import com.mypj.aaa.dto.UserDto;
import com.mypj.aaa.entity.Authority;
import com.mypj.aaa.entity.User;
import com.mypj.aaa.repository.UserRepository;
import com.mypj.aaa.util.SecurityUtil;
import java.util.Collections;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public User signup(UserDto userDto){
    if(userRepository.findOneWithAuthoritiesByUserId(userDto.getUserId()).isPresent()){
      throw new RuntimeException("이미 가입되어 있는 유저입니다.");
    }

    Authority authority = Authority.builder().autorityName("ROLE_USER").build();

    User user = User.builder()
        .userId(userDto.getUserId())
        .password(passwordEncoder.encode(userDto.getPassword()))
        .authorities(Collections.singleton(authority))
        .activated(true)
        .build();

    return userRepository.save(user);
  }

  @Transactional(readOnly = true)
  public User getUserWithAuthorities(String userId){
    return userRepository.findOneWithAuthoritiesByUserId(userId).get();
  }

  @Transactional(readOnly = true)
  public User getMyUserWithAuthorities(){
    return SecurityUtil.getCurrentUserId().flatMap(userRepository::findOneWithAuthoritiesByUserId).get();
  }




}
