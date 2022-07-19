package com.mypj.aaa.controller;

import com.mypj.aaa.domain.dto.ScheduleDto;
import com.mypj.aaa.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/schedules")
@RequiredArgsConstructor
@RestController
public class ScheduleController {
  private final ScheduleService scheduleService;

  @PostMapping("/{schedulerId}")
  public ResponseEntity newSchedule(@PathVariable long schedulerId, @RequestBody ScheduleDto scheduleDto){
    scheduleService.newSchedule(schedulerId,scheduleDto);
    return ResponseEntity.ok(HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity getSchedule(@PathVariable Long id){
    return ResponseEntity.ok(scheduleService.getSchedule(id));
  }

}