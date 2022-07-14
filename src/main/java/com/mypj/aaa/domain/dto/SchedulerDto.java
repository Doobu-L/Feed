package com.mypj.aaa.domain.dto;

import com.mypj.aaa.domain.entity.Scheduler;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SchedulerDto {
  private List<ScheduleDto> schedules;
  private Long id;
  private String title;

  public SchedulerDto(Scheduler scheduler){
    this.schedules = scheduler.getSchedules().stream().map(ScheduleDto::new).collect(Collectors.toList());
    this.id = scheduler.getId();
    this.title = scheduler.getTitle();
  }
}
