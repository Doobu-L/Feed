package com.mypj.aaa.entity;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
public class User extends BaseEntity{

  @Column
  private String userId;
  @Column
  private String password;
  @Column
  private String email;
  @Column
  private boolean activated;

  @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
  @JoinTable(
      name = "user_authority",
      joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "authority_name",referencedColumnName = "authority_name")}
      )
  private Set<Authority> authorities;

}
