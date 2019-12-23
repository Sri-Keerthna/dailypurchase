package com.sprintstrickers.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprintstrickers.ecommerce.dto.LoginRequestDto;
import com.sprintstrickers.ecommerce.dto.LoginResponseDto;
import com.sprintstrickers.ecommerce.exception.InvalidUser;
import com.sprintstrickers.ecommerce.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	@PostMapping("/users/login")
	public LoginResponseDto userLogin(LoginRequestDto loginRequestDto) throws InvalidUser {
		
		LoginResponseDto loginResponseDto=userService.userLogin(loginRequestDto);
		return loginResponseDto;

	}
}
