package com.mypj.aaa.controller;

import com.mypj.aaa.domain.dto.SchedulerDto;
import com.mypj.aaa.domain.entity.User;
import com.mypj.aaa.service.FollowSchedulerService;
import com.mypj.aaa.service.UserSchedulerService;
import com.mypj.aaa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/users/schedulers")
@RequiredArgsConstructor
@RestController
public class UserSchedulerController {
  private final UserSchedulerService userSchedulerService;
  private final UserService userService;
  private final FollowSchedulerService followSchedulerService;

  @PostMapping
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity newScheduler(@RequestBody SchedulerDto req){
    User user = userService.getMyUserWithAuthorities();

    if(userSchedulerService.checkTitle(user,req.getTitle()))
      return ResponseEntity.badRequest().body("동일한 스케줄러가 존재합니다.");
    userSchedulerService.newScheduler(user,req);

    return ResponseEntity.ok(HttpStatus.CREATED);
  }

  /*
  * 유저가 등록한 스케줄러들을 가져온다
  * */
  @GetMapping("/{userId}")
  public ResponseEntity getUserSchedulers(@PathVariable Long userId){
    return ResponseEntity.ok(userSchedulerService.getUserSchedulers(userId));
  }


  @PostMapping("/follow/{schedulerId}")
  //@PreAuthorize("hasAnyRole('USER')")
  public ResponseEntity saveFollowScheduler(@PathVariable Long schedulerId){
    User user = userService.getMyUserWithAuthorities();

    followSchedulerService.saveFollowScheduler(user,schedulerId);
    return ResponseEntity.ok(HttpStatus.CREATED);
  }


}

