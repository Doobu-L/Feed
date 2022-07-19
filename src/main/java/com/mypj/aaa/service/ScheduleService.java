package com.mypj.aaa.service;

import com.mypj.aaa.domain.dto.ScheduleDto;
import com.mypj.aaa.domain.entity.Schedule;
import com.mypj.aaa.domain.entity.Scheduler;
import com.mypj.aaa.repository.ScheduleRepository;
import com.mypj.aaa.repository.SchedulerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ScheduleService {

  private final ScheduleRepository scheduleRepository;
  private final SchedulerRepository schedulerRepository;

  @Transactional
  public void newSchedule(long schedulerId,ScheduleDto scheduleDto){
    Scheduler scheduler = schedulerRepository.findById(schedulerId).orElseThrow(() -> new RuntimeException("해당 스케쥴러가 없습니다."));
    Schedule newSchedule = Schedule.builder().title(scheduleDto.getTitle()).targetDateTime(scheduleDto.getTargetDateTime()).build();
    scheduler.getSchedules().add(newSchedule);
    schedulerRepository.save(scheduler);
  }

  public ScheduleDto getSchedule(Long id){
    return scheduleRepository.findById(id).map(ScheduleDto::new).orElseThrow(() -> new RuntimeException("해당 스케쥴이 없습니다."));


  }


}
