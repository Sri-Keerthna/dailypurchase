package com.sprintstrickers.ecommerce.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.sprintstrickers.ecommerce.dto.LoginRequestDto;
import com.sprintstrickers.ecommerce.dto.LoginResponseDto;
import com.sprintstrickers.ecommerce.exception.InvalidUser;
import com.sprintstrickers.ecommerce.service.UserService;
import com.sprintstrickers.ecommerce.utils.ApiConstant;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	@InjectMocks
	UserController userController;
	@Mock
	UserService userService;

	LoginRequestDto loginRequestDto = null;
	LoginResponseDto loginResponseDto = null;

	@Before
	public void setUp() {
		loginRequestDto = new LoginRequestDto();
		loginRequestDto.setUserEmail("nnaresht21@gmail.com");
		loginRequestDto.setPassword("iitdelhi");
		loginResponseDto = new LoginResponseDto();
		loginResponseDto.setMessage(ApiConstant.LOGIN_SUCCESS);
		loginResponseDto.setStatusCode(200);
	}

	@Test
	public void testLoginUser() throws InvalidUser {
Mockito.when(userService.userLogin(loginRequestDto)).thenReturn(loginResponseDto);
LoginResponseDto response=userController.userLogin(loginRequestDto);
assertEquals(ApiConstant.LOGIN_SUCCESS, response.getMessage());
	}
}
