package com.mypj.aaa.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@ToString
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Entity
public class Schedule extends BaseEntity {

  @Column
  private String title;

  @Column
  private LocalDateTime targetDateTime;

  @Builder
  public Schedule(String title,LocalDateTime targetDateTime){
    this.title = title;
    this.targetDateTime = targetDateTime;
  }


}
