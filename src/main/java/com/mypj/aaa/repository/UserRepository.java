package com.mypj.aaa.repository;

import com.mypj.aaa.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

  @EntityGraph(attributePaths = "authorities")
  Optional<User> findOneWithAuthoritiesByUserId(String userId);

}
