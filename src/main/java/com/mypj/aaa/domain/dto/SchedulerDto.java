package com.mypj.aaa.domain.dto;

import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SchedulerDto {
  private List<ScheduleDto> schedules;
  private String title;
}
