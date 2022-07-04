package com.mypj.aaa.securityTest.annonymous;

import com.mypj.aaa.controller.UserController;
import com.mypj.aaa.domain.dto.UserDto;
import com.mypj.aaa.domain.entity.User;
import com.mypj.aaa.repository.UserRepository;
import com.mypj.aaa.service.UserService;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class AnnonymousTest {

  @Autowired
  private UserService userService;
  @Autowired
  private UserController userController;

  private UserRepository userRepository;
  @Autowired private PasswordEncoder passwordEncoder ;


  @BeforeEach
  void init() {
    userRepository = mock(UserRepository.class);
    this.userService = new UserService(userRepository, passwordEncoder);
  }



  @DisplayName("회원가입 테스트 1# - 아이디 중복")
  @Test
  void isExist_UserId(){
    User user = User.builder()
        .userId("test").build();

    UserDto userDto = UserDto.builder().userId("test").build();

    when(userRepository.findOneWithAuthoritiesByUserId(userDto.getUserId())).thenReturn(Optional.of(user));

    Assertions.assertThrows(RuntimeException.class,()->{userService.signup(userDto);});
  }

  @DisplayName("회원가입 테스트 2# - 회원 가입")
  @Test
  void signup(){
    UserDto userDto = UserDto.builder()
        .userId("TEST2")
        .password("1234")
        .email("emin@min.com")
        .build();

    when(userRepository.findOneWithAuthoritiesByUserId(userDto.getUserId())).thenReturn(Optional.of(null));

    assertThat(userService.signup(userDto)).isExactlyInstanceOf(User.class);


  }




}
