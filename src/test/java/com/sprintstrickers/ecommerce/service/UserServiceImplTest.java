package com.sprintstrickers.ecommerce.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.sprintstrickers.ecommerce.dto.LoginRequestDto;
import com.sprintstrickers.ecommerce.dto.LoginResponseDto;
import com.sprintstrickers.ecommerce.entity.User;
import com.sprintstrickers.ecommerce.exception.InvalidUser;
import com.sprintstrickers.ecommerce.repository.UserRepository;
import com.sprintstrickers.ecommerce.utils.ApiConstant;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
	@InjectMocks
	UserServiceImpl userServiceImpl;
	
	@Mock
	UserRepository userRepository;
	LoginRequestDto loginRequestDto=null;
	LoginResponseDto loginResponseDto=null;
	User user=null;
	User user1=null;
	@Before
	public void setUp() {
		loginRequestDto=new LoginRequestDto();
		loginRequestDto.setUserEmail("nnaresht21@gmail.com");
		loginRequestDto.setPassword("iitdelhi");
		loginResponseDto=new LoginResponseDto();
		loginResponseDto.setMessage(ApiConstant.LOGIN_SUCCESS);
		loginResponseDto.setStatusCode(200);
		user=new User();
		user.setEmail("nnaresht21@gmail.com");
		user.setPassword("iitdelhi");
	 user1=new User();
	user1.setEmail("nnaresht21@gmail.com");
	user1.setPassword("user@123");
		
	}
	@Test
	public void testLoginUser() throws InvalidUser {
		Mockito.when(userRepository.findByEmail("nnaresht21@gmail.com")).thenReturn(Optional.of(user));
		LoginResponseDto response=userServiceImpl.userLogin(loginRequestDto);
		assertEquals(ApiConstant.LOGIN_SUCCESS, response.getMessage());
	}
	
	@Test
	public void testLoginUserInvalidCredential() throws InvalidUser {
		Mockito.when(userRepository.findByEmail("nnaresht21@gmail.com")).thenReturn(Optional.of(user1));
		LoginResponseDto response=userServiceImpl.userLogin(loginRequestDto);
		assertEquals(ApiConstant.LOGIN_FAILURE, response.getMessage());
	}
	@Test(expected = InvalidUser.class)
	public void testLoginUserNotFound() throws InvalidUser {
		Optional<User> optionalUser=Optional.ofNullable(null);
		Mockito.when(userRepository.findByEmail("nnares21@gmail.com")).thenReturn(optionalUser);
		userServiceImpl.userLogin(loginRequestDto);
	}

}
