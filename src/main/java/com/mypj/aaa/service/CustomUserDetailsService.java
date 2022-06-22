package com.mypj.aaa.service;

import com.mypj.aaa.entity.User;
import com.mypj.aaa.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    return userRepository.findOneWithAuthoritiesByUserId(userId)
        .map(user -> createUser(userId,user))
        .orElseThrow(() -> new UsernameNotFoundException(userId + " -> 해당 User를 찾을 수 없습니다."));
  }

  private org.springframework.security.core.userdetails.User createUser(String userId, User user){
    if(!user.isActivated()){
      throw new RuntimeException(userId + " >> 활성화되어 있지 않습니다.");
    }
    List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
        .map(authority -> new SimpleGrantedAuthority(authority.getAutorityName()))
        .collect(Collectors.toList());;
        return new org.springframework.security.core.userdetails.User(user.getUserId(),user.getPassword(),grantedAuthorities);
  }

}
