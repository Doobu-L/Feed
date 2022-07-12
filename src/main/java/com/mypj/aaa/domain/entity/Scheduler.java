package com.mypj.aaa.domain.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@Getter
@ToString
@DynamicInsert
@DynamicUpdate
@Entity
public class Scheduler extends BaseEntity {

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "scheduler_id")
  private Set<Schedule> schedules;

  @ManyToOne
  private User user;

  @Column
  private String title;


}
