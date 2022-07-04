package com.mypj.aaa.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
@Entity
public class Feed extends BaseEntity{

  @ManyToOne(cascade = CascadeType.ALL,optional = false,fetch = FetchType.LAZY)
  private User user;

  @Column
  private String title;

  @Column
  private String content;

  @Column
  private int viewCount;

  @PostLoad
  private void viewCount(){

  }

}
