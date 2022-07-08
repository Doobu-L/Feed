package com.mypj.aaa.service;

import com.mypj.aaa.domain.dto.FeedDto;
import com.mypj.aaa.domain.entity.Feed;
import com.mypj.aaa.domain.entity.User;
import com.mypj.aaa.repository.FeedRepository;
import com.mypj.aaa.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FeedService {

  private final FeedRepository feedRepository;
  private final UserRepository userRepository;

  @Transactional
  public List<FeedDto> getAllFeedList(){
    return feedRepository.findAll().stream().map(FeedDto::new).collect(Collectors.toList());
  }

  @Transactional
  public FeedDto getFeed(long id){
    Feed feed = feedRepository.findById(id).orElseThrow(()->new RuntimeException("존재하지 않는 게시글입니다."));
    return new FeedDto(feed);
  }

  @Transactional
  public void newFeed(FeedDto feedDto){
    User user =  userRepository.findOneWithAuthoritiesByUserId(feedDto.getUserId()).orElseThrow(()->new RuntimeException("존재하지않는 유저입니다."));
    Feed feed = Feed.builder().title(feedDto.getTitle()).content(feedDto.getContent()).user(user).build();
    feedRepository.save(feed);
  }
}
