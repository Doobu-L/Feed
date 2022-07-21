package com.mypj.aaa.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mypj.aaa.domain.entity.Schedule;
import io.swagger.v3.oas.annotations.Hidden;
import java.text.SimpleDateFormat;
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

  private int month;
  private int day;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Seoul")
  private int hour;
  private int minute;

  public ScheduleDto(Schedule schedule){
    this.id = id;
    this.title = schedule.getTitle();
    this.targetDateTime = schedule.getTargetDateTime();
    this.month = this.targetDateTime.getMonthValue();
    this.day = this.targetDateTime.getDayOfMonth();
    this.hour = this.targetDateTime.getHour();
    this.minute = this.targetDateTime.getMinute();
  }

}
