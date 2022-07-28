package com.mypj.aaa.service;

import com.mypj.aaa.domain.entity.FollowScheduler;
import com.mypj.aaa.domain.entity.Scheduler;
import com.mypj.aaa.domain.entity.User;
import com.mypj.aaa.repository.FollowSchedulerRepository;
import com.mypj.aaa.repository.SchedulerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowSchedulerService {
  private final FollowSchedulerRepository followSchedulerRepository;
  private final SchedulerRepository schedulerRepository;

  @Transactional
  public void saveFollowScheduler(User user, long schedulerId){
    Scheduler scheduler = schedulerRepository.findById(schedulerId).orElseThrow(()->new RuntimeException("해당 스케쥴러가 존재하지 않습니다."));
    FollowScheduler followScheduler = new FollowScheduler(user,scheduler);
    followSchedulerRepository.save(followScheduler);
  }

}
