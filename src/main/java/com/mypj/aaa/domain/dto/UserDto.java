package com.mypj.aaa.domain.dto;

import com.mypj.aaa.domain.entity.User;
import java.util.Set;
import java.util.stream.Collectors;
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
public class UserDto {

  private Set<SchedulerDto> schedulerDtos;

  @NotNull
  @Size(min = 3,max = 50)
  private String userId;

  @NotNull
  @Size(min = 3,max = 100)
  private String password;

  @NotNull
  @Size(min = 3,max = 50)
  private String email;

  public UserDto(User user){
    this.schedulerDtos = user.getSchedulers().stream().map(SchedulerDto::new).collect(Collectors.toSet());
    this.userId = user.getUserId();
    this.password = user.getEmail();
  }
}
