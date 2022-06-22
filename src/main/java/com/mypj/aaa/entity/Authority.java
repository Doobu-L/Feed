package com.mypj.aaa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "authority")
@Entity
public class Authority{

  @Id
  @Column(name = "authority_name")
  private String autorityName;

}
