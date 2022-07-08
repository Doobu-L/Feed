package com.mypj.aaa.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mypj.aaa.domain.dto.UserDto;
import com.mypj.aaa.repository.UserRepository;
import com.mypj.aaa.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
class UserControllerTest {
  @InjectMocks
  UserController userController;

  ObjectMapper objectMapper;

  @Mock
  UserService userService;
  @Mock
  UserRepository userRepository;
  @Mock
  PasswordEncoder passwordEncoder ;

  MockMvc mockMvc;

  @BeforeEach
  public void setUp(){
    this.objectMapper = new ObjectMapper();
    this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    this.userService = new UserService(userRepository,passwordEncoder);
  }

  @Test
  @WithAnonymousUser
  public void signup() throws Exception {
    UserDto userDto = UserDto.builder().userId("AAAA").password("1234").email("wiwi@n.com").build();

    mockMvc.perform(post("/api/users/signup").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(userDto)).accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

}