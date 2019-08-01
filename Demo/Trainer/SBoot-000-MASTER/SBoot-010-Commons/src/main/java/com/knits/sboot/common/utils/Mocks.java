package com.knits.sboot.common.utils;

import com.knits.sboot.common.beans.UserDto;

public class Mocks {

	public static UserDto mockUser(){
		UserDto mock = new UserDto();
		mock.setName("Stefano Fiorenza");	
		mock.setEmail("stefanofiorenza@email.com");
		mock.setTelephone("09573625554");
		mock.setCity("Tallinn");
		return mock;
	}
}
