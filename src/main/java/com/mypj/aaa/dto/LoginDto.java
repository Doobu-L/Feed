package com.mypj.aaa.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

  @NotNull
  @Size(min = 3, max = 50)
  private String userId;

  @NotNull
  @Size(min = 3,max = 100)
  private String password;

  public static void main(String[] args) {

  }
}
