package com.mypj.aaa.repository;

import com.mypj.aaa.domain.entity.Category;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {

  Set<Category> findByIdIn(List<Long> ids);

}
