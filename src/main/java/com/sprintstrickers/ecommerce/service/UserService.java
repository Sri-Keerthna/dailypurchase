package com.sprintstrickers.ecommerce.service;

import com.sprintstrickers.ecommerce.dto.LoginRequestDto;
import com.sprintstrickers.ecommerce.dto.LoginResponseDto;
import com.sprintstrickers.ecommerce.exception.InvalidUser;

public interface UserService {
	public	LoginResponseDto userLogin(LoginRequestDto loginRequestDto) throws InvalidUser;
}
