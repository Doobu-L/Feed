package com.mypj.aaa.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

@Getter @ToString
@DynamicUpdate
@Entity
public class Category extends BaseEntity {

  @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
  @JsonIgnore
  private List<FeedCategory> feedCategories;

  @Column
  private String name;


}
