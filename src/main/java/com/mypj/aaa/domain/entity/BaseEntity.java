package com.mypj.aaa.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@MappedSuperclass
public abstract class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private LocalDateTime createAt;

  @Column
  private LocalDateTime updateAt;

  @Column(length = 1)
  private String delYn;

  @PostPersist
  public void onCreated(){
    this.createAt = LocalDateTime.now();
    this.delYn = "N";
  }

  @PostUpdate
  public void onUpdated(){
    this.updateAt = LocalDateTime.now();
  }

}
