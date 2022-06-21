package com.mypj.aaa.domain;

import javax.persistence.Entity;
import lombok.Getter;


@Getter
@Entity
public class User extends BaseEntity{

  private String userId;
  private String password;
  private String email;

}
