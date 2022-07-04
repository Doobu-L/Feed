package com.mypj.aaa.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.transaction.annotation.Transactional;

@Getter @ToString
@DynamicUpdate
@Entity
public class Feed extends BaseEntity{

  @ManyToOne(cascade = CascadeType.ALL,optional = false,fetch = FetchType.LAZY)
  @JsonIgnore
  private User user;

  @Column
  private String title;

  @Column
  private String content;

  @Column
  private int viewCount;

  public Feed() {
    super();
  }

  @PostLoad
  public void viewCount(){
    this.viewCount +=1;
  }

  @Builder
  public Feed(User user, String title, String content){
    super();
    this.user = user;
    this.title = title;
    this.content = content;
  }

}
