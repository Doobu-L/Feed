package com.mypj.aaa.repository;

import com.mypj.aaa.domain.entity.Scheduler;
import com.mypj.aaa.domain.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulerRepository extends JpaRepository<Scheduler,Long> {

  Optional<Scheduler> findByTitleAndUser(String title, User user);

  List<Scheduler> findByIdIn(List<Long> ids);
}
