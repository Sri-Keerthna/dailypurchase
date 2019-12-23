package com.sprintstrickers.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequestDto {
 private String userEmail;
 private String password;
}
