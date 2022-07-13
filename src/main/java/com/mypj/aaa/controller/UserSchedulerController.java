package com.mypj.aaa.controller;

import com.mypj.aaa.domain.dto.SchedulerDto;
import com.mypj.aaa.domain.entity.User;
import com.mypj.aaa.service.UserSchedulerService;
import com.mypj.aaa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/scheduler")
@RequiredArgsConstructor
@RestController
public class UserSchedulerController {
  private final UserSchedulerService userSchedulerService;
  private final UserService userService;

  @PostMapping
  @PreAuthorize("hasAnyRole('USER')")
  public ResponseEntity newScheduler(@RequestBody SchedulerDto req){
    User user = userService.getMyUserWithAuthorities();
    userSchedulerService.newScheduler(user,req);
    return ResponseEntity.ok(HttpStatus.CREATED);
  }


}
