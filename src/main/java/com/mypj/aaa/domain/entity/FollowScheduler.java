package com.mypj.aaa.domain.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@Entity
public class FollowScheduler extends BaseEntity {

  @ManyToOne
  private User user;

  @ManyToOne
  private Scheduler scheduler;

  public FollowScheduler(User user,Scheduler scheduler){
    this.user = user;
    this.scheduler = scheduler;
  }

}
