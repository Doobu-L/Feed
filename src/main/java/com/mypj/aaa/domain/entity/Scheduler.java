package com.mypj.aaa.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;


@Getter
@ToString
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Entity
@Where(clause = "del_yn='N'")
public class Scheduler extends BaseEntity {

  @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
  @JoinColumn(name = "scheduler_id")
  @JsonIgnore
  private Set<Schedule> schedules = new HashSet<>();

  @ManyToOne
  @JsonIgnore
  private User user;

  @Column
  private String title;

  @Builder
  public Scheduler(User user, List<Schedule> schedules,String title) {
    this.user = user;
    if(!schedules.isEmpty()){
      schedules.forEach(schedule -> this.schedules.add(schedule));
    }
    this.title = title;
  }
}
