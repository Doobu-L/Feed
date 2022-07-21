package com.mypj.aaa.service;

import com.mypj.aaa.domain.dto.UserDto;
import com.mypj.aaa.domain.entity.Authority;
import com.mypj.aaa.domain.entity.User;
import com.mypj.aaa.repository.UserRepository;
import com.mypj.aaa.util.SecurityUtil;
import java.util.Collections;
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
  public UserDto getUserWithAuthorities(Long id){
    return userRepository.findById(id).map(UserDto::new).orElseThrow(()-> new RuntimeException("해당 유저는 존재하지 않습니다."));
  }

  @Transactional(readOnly = true)
  public User getMyUserWithAuthorities(){
    return SecurityUtil.getCurrentUserId().flatMap(userRepository::findOneWithAuthoritiesByUserId).get();
  }




}
