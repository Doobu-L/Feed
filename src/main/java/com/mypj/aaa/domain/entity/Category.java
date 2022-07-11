package com.mypj.aaa.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicUpdate;

@Getter @ToString
@DynamicUpdate
@Entity
public class Category extends BaseEntity {

  @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
  @JsonIgnore
  @BatchSize(size = 100)
  private Set<FeedCategory> feedCategories;

  @Column
  private String name;


}
