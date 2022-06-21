package com.mypj.aaa.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

@MappedSuperclass
public abstract class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private LocalDateTime createAt;

  @Column
  private LocalDateTime updateAt;

  @PostPersist
  public void onCreated(){
    this.createAt = LocalDateTime.now();
  }

  @PostUpdate
  public void onUpdated(){
    this.updateAt = LocalDateTime.now();
  }

}
