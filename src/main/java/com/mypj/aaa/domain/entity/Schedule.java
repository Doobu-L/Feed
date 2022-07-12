package com.mypj.aaa.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@ToString
@DynamicInsert
@DynamicUpdate
@Entity
public class Schedule extends BaseEntity {

  @Column
  private String title;

  @Column
  private LocalDateTime targetDateTime;

  private int year;
  private int month;
  private int day;
  private int time;



}
