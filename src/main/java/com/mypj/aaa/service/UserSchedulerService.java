package com.mypj.aaa.service;

import com.mypj.aaa.domain.dto.SchedulerDto;
import com.mypj.aaa.domain.entity.Schedule;
import com.mypj.aaa.domain.entity.Scheduler;
import com.mypj.aaa.domain.entity.User;
import com.mypj.aaa.repository.ScheduleRepository;
import com.mypj.aaa.repository.SchedulerRepository;
import com.mypj.aaa.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserSchedulerService {

  private final UserRepository userRepository;
  private final SchedulerRepository schedulerRepository;
  private final ScheduleRepository scheduleRepository;

  @Transactional
  public void newScheduler(User user, SchedulerDto req) {
    List<Schedule> schedules = req.getSchedules().stream().map(
        in -> Schedule.builder().title(in.getTitle()).targetDateTime(in.getTargetDateTime())
            .build()).collect(
        Collectors.toList());
    Scheduler scheduler = Scheduler.builder().user(user).schedules(schedules).title(req.getTitle()).build();
    schedulerRepository.save(scheduler);
  }

  public boolean checkTitle(User user,String title){
    return schedulerRepository.findByTitleAndUser(title,user).isPresent()?true:false;
  }

  @Transactional
  public List<SchedulerDto> getUserSchedulers(long userId){
    User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("해당하는 유저가 없습니다."));
    return user.getSchedulers().stream().map(SchedulerDto::new).collect(Collectors.toList());


  }

}
