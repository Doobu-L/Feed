package com.mypj.aaa.securityTest;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mypj.aaa.repository.UserRepository;
import com.mypj.aaa.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@SpringBootTest
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class SecurityTest {

  MockMvc mockMvc;

  @Autowired
  WebApplicationContext context;
  ObjectMapper objectMapper;

  @Mock
  UserService userService;
  @Mock
  UserRepository userRepository;
  @Mock
  PasswordEncoder passwordEncoder ;

  @BeforeEach
  public void setup(){
    this.mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).build();
    this.objectMapper = new ObjectMapper();
    this.userService = new UserService(userRepository,passwordEncoder);
  }

  @Test
  @WithMockUser(username = "test",roles = "USER")
  void getMyUserInfo() throws Exception {
    mockMvc.perform(get("/api/users"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser(username = "test",roles = "ADMIN")
  void getUserINfo() throws Exception {
    mockMvc.perform(get("/api/users/1"))
        .andDo(print())
        .andExpect(status().isOk());
  }

}
