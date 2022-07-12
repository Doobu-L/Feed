package com.mypj.aaa.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter @ToString
@DynamicUpdate
@DynamicInsert
@Entity
public class Feed extends BaseEntity{

  @ManyToOne(cascade = CascadeType.ALL,optional = false,fetch = FetchType.LAZY)
  @JsonIgnore
  private User user;

  @OneToMany(mappedBy = "feed",fetch = FetchType.LAZY)
  @Cascade(org.hibernate.annotations.CascadeType.ALL)
  @JsonIgnore
  @BatchSize(size = 100)
  private Set<FeedCategory> feedCategories = new HashSet<>();

  @Column
  private String title;

  @Column
  private String content;

  @Column
  private int viewCount;

  public Feed() {
    super();
  }

  public void viewCount(){
    this.viewCount +=1;
  }

  @Builder
  public Feed(User user, String title, String content, List<Category> categories){
    super();
    this.user = user;
    this.title = title;
    this.content = content;
    Set<FeedCategory> feedCategories = categories.stream().map(category -> new FeedCategory(this,category)).collect(
        Collectors.toSet());
    this.feedCategories.addAll(feedCategories);
  }


}
