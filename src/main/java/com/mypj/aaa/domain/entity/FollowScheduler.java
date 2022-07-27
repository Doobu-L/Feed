package com.mypj.aaa.domain.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Entity
public class FollowScheduler extends BaseEntity {

  @ManyToOne
  private User user;

  @ManyToOne
  private Scheduler scheduler;

}
