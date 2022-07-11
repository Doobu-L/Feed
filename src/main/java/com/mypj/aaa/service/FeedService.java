package com.mypj.aaa.service;

import com.mypj.aaa.domain.dto.FeedDto;
import com.mypj.aaa.domain.entity.Category;
import com.mypj.aaa.domain.entity.Feed;
import com.mypj.aaa.domain.entity.FeedCategory;
import com.mypj.aaa.domain.entity.User;
import com.mypj.aaa.repository.CategoryRepository;
import com.mypj.aaa.repository.FeedRepository;
import com.mypj.aaa.repository.UserRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FeedService {

  private final FeedRepository feedRepository;
  private final UserRepository userRepository;
  private final CategoryRepository categoryRepository;

  @Transactional
  public List<FeedDto> getAllFeedList(){
    return feedRepository.findAll().stream().map(FeedDto::new).collect(Collectors.toList());
  }

  /*
  *
  * Todo - 다른 방법 찾아보자.
  * */
  @Transactional
  public Set<FeedDto> getFeedListByCategory(List<Long> ids){
    Set<Category> categories = categoryRepository.findByIdIn(ids);
    Set<Feed> feeds = categories.stream().flatMap(category -> category.getFeedCategories().stream().map(FeedCategory::getFeed)).collect(
        Collectors.toSet());
    return feeds.stream().map(FeedDto::new).collect(Collectors.toSet());
  }

  @Transactional
  public FeedDto getFeed(long id){
    Feed feed = feedRepository.findById(id).orElseThrow(()->new RuntimeException("존재하지 않는 게시글입니다."));
    feed.viewCount();
    return new FeedDto(feed);
  }

  @Transactional
  public void newFeed(FeedDto feedDto){
    User user =  userRepository.findOneWithAuthoritiesByUserId(feedDto.getUserId()).orElseThrow(()->new RuntimeException("존재하지않는 유저입니다."));
    List<Category> categories = feedDto.getCategories().stream()
        .map(category -> categoryRepository.findById(category.getId())
            .orElseThrow(()->new RuntimeException("존재하지 않는 카테고리입니다.")))
        .collect(Collectors.toList());
    Feed feed = Feed.builder().title(feedDto.getTitle()).content(feedDto.getContent()).user(user).categories(categories).build();
    feedRepository.save(feed);
  }
}
