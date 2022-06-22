package com.mypj.aaa.controller;

import com.mypj.aaa.dto.UserDto;
import com.mypj.aaa.entity.User;
import com.mypj.aaa.service.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  @PostMapping("/signup")
  public ResponseEntity<User> signup(
      @Valid @RequestBody UserDto userDto
  ){
    return ResponseEntity.ok(userService.signup(userDto));
  }

  @GetMapping("")
  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  public ResponseEntity<User> getMyUserInfo(){
    return ResponseEntity.ok(userService.getMyUserWithAuthorities().get());
  }

  @GetMapping("/{userId}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<User> getUserINfo(@PathVariable("userId") String userId){
    return ResponseEntity.ok(userService.getUserWithAuthorities(userId).get());
  }

}
