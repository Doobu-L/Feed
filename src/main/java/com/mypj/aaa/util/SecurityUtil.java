package com.mypj.aaa.util;

import java.util.Optional;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Slf4j
@NoArgsConstructor
public class SecurityUtil {
  public static Optional<String> getCurrentUserId(){
    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if(authentication == null){
      log.debug("Security Context에 인증 정보가 없습니다.");
      return Optional.empty();
    }

    String userId = null;
    if(authentication.getPrincipal() instanceof UserDetails){
      UserDetails securityUser = (UserDetails) authentication.getPrincipal();
      userId = securityUser.getUsername();
    } else if (authentication.getPrincipal() instanceof  String){
      userId = (String) authentication.getPrincipal();
    }
    return Optional.ofNullable(userId);

  }
}
