package com.mypj.aaa.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import com.mypj.aaa.domain.dto.SchedulerDto;
import com.mypj.aaa.domain.entity.Scheduler;
import com.mypj.aaa.domain.entity.User;
import com.mypj.aaa.repository.SchedulerRepository;
import com.mypj.aaa.repository.UserRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserSchedulerServiceTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private SchedulerRepository schedulerRepository;

  @InjectMocks
  private UserSchedulerService userSchedulerService;

  @Test
  void newScheduler() {
  }

  @Test
  void checkTitle() {
    User user = User.builder().userId("test").build();
    String title = "title";
    Scheduler scheduler = Scheduler.builder().user(user).title("title").build();
    when(schedulerRepository.findByTitleAndUser(title,user)).thenReturn(Optional.of(scheduler));

    Boolean check = userSchedulerService.checkTitle(user,title);
    Boolean check2 = userSchedulerService.checkTitle(user,"TTTT");

    Assertions.assertThat(check).isFalse();
    Assertions.assertThat(check2).isTrue();

  }

  @Test
  void getUserSchedulers() {
    final Long id = 1L;
    Set<Scheduler> schedulers = new HashSet<>();
    Scheduler scheduler = Scheduler.builder().title("testSchedule").build();
    schedulers.add(scheduler);
    User user = User.builder().userId("test").schedulers(schedulers).build();
    given(userRepository.findById(id)).willReturn(Optional.of(user));

    List<SchedulerDto> returnSchedulers =  userSchedulerService.getUserSchedulers(id);

    Assertions.assertThat(returnSchedulers).isNotNull();
    Assertions.assertThat(returnSchedulers.get(0).getTitle()).isEqualTo("testSchedule");

  }
}