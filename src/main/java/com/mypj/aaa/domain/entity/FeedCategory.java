package com.mypj.aaa.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@ToString
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@Entity
public class FeedCategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "feed_id")
  private Feed feed;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  public FeedCategory(Feed feed, Category category){
    this.feed = feed;
    this.category = category;
  }

}
