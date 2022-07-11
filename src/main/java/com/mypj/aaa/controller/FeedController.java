package com.mypj.aaa.controller;

import com.mypj.aaa.domain.dto.FeedDto;
import com.mypj.aaa.service.FeedService;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/feeds")
public class FeedController {

  private final FeedService feedService;

  @GetMapping
  public List<FeedDto> getAllFeedList(){
    return feedService.getAllFeedList();
  }

  @GetMapping("/{id}")
  public FeedDto getFeed(@PathVariable long id){
    return feedService.getFeed(id);
  }

  @GetMapping("/categories")
  public Set<FeedDto> getFeedsByCategories(@RequestParam String feedCategories){
    List<Long> ids = Arrays.stream(feedCategories.split(",")).mapToLong(id->Long.valueOf(id)).boxed().collect(
        Collectors.toList());
    return feedService.getFeedListByCategory(ids);
  }

  @PostMapping
  public void newFeed(@RequestBody FeedDto feedDto){
    feedService.newFeed(feedDto);
  }



}
