package com.mypj.aaa.repository;

import com.mypj.aaa.domain.entity.Feed;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FeedRepository extends JpaRepository<Feed,Long> {
}
