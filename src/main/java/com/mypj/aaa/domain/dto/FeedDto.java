package com.mypj.aaa.domain.dto;

import com.mypj.aaa.domain.entity.Feed;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedDto {

  private String title;
  private String content;
  private String userId;
  private int viewCount;

  public FeedDto(Feed feed){
    this.title = feed.getTitle();
    this.content = feed.getContent();
    this.userId = feed.getUser().getUserId();
    this.viewCount = feed.getViewCount();
  }

}
