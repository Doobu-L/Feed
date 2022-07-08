package com.mypj.aaa.domain.dto;

import com.mypj.aaa.domain.entity.Category;
import com.mypj.aaa.domain.entity.Feed;
import com.mypj.aaa.domain.entity.FeedCategory;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedDto {

  private List<Category> categories = new ArrayList<>();

  private String title;
  private String content;
  private String userId;
  private int viewCount;

  public FeedDto(Feed feed){
    this.title = feed.getTitle();
    this.content = feed.getContent();
    this.userId = feed.getUser().getUserId();
    this.viewCount = feed.getViewCount();
    this.categories = feed.getFeedCategories().stream().map(FeedCategory::getCategory).collect(
        Collectors.toList());
  }

}
