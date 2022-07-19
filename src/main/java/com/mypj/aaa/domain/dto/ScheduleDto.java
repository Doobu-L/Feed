package com.mypj.aaa.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mypj.aaa.domain.entity.Schedule;
import io.swagger.v3.oas.annotations.Hidden;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ScheduleDto {

  @Hidden
  private Long id;

  private String title;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
  private LocalDateTime targetDateTime;

  public ScheduleDto(Schedule schedule){
    this.title = schedule.getTitle();
    this.targetDateTime = schedule.getTargetDateTime();
  }

}
