package com.sprintstrickers.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprintstrickers.ecommerce.dto.LoginRequestDto;
import com.sprintstrickers.ecommerce.dto.LoginResponseDto;
import com.sprintstrickers.ecommerce.entity.User;
import com.sprintstrickers.ecommerce.exception.InvalidUser;
import com.sprintstrickers.ecommerce.repository.UserRepository;
import com.sprintstrickers.ecommerce.utils.ApiConstant;

/**
 * @author Naresh
 *
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	/**
	 *
	 */
	@Override
	public LoginResponseDto userLogin(LoginRequestDto loginRequestDto) throws InvalidUser {
		LoginResponseDto loginResponseDto=new LoginResponseDto();
		Optional<User> optionalUser=userRepository.findByEmail(loginRequestDto.getUserEmail());
		if(optionalUser.isPresent()) {
			if(optionalUser.get().getEmail().equals(loginRequestDto.getUserEmail()) && optionalUser.get().getPassword().equals(loginRequestDto.getPassword())){
				loginResponseDto=new LoginResponseDto();
					loginResponseDto.setMessage(ApiConstant.LOGIN_SUCCESS);
					loginResponseDto.setStatusCode(201);
					
			}else {
				loginResponseDto.setMessage(ApiConstant.LOGIN_FAILURE);
			}
		}else {
			throw new InvalidUser(ApiConstant.USER_NOT_FOUND);
		}
		return loginResponseDto;
	}



}
