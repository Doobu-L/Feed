package com.mypj.aaa.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;


@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@DynamicUpdate
@Entity
@Where(clause = "del_yn='N'")
public class User extends BaseEntity{

  @OneToMany(fetch = FetchType.LAZY,mappedBy = "user",cascade = CascadeType.ALL)
  @BatchSize(size = 100)
  @JsonIgnore
  private Set<Scheduler> schedulers;

  @OneToMany(fetch = FetchType.LAZY,mappedBy = "user",cascade = CascadeType.ALL)
  @BatchSize(size = 100)
  @JsonIgnore
  private Set<FollowScheduler> followSchedulers;

  @Column(length = 50,unique = true)
  private String userId;

  @JsonIgnore
  @Column(length = 100)
  private String password;

  @Column(length = 50)
  private String email;

  @JsonIgnore
  @Column
  private boolean activated;

  @ManyToMany
  @JoinTable(
      name = "user_authority",
      joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "authority_name",referencedColumnName = "authority_name")}
      )
  private Set<Authority> authorities;

}
