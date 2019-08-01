package com.knits.sboot.common.service;

import java.util.List;

import com.knits.sboot.common.beans.UserDto;

public interface UserService {

	Long save(UserDto user);
	
	List<UserDto> findUsersByCity(String city);
	
	void updateUser (UserDto user);
}
