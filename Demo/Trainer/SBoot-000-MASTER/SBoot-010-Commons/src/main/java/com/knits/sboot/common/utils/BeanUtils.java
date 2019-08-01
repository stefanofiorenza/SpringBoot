package com.knits.sboot.common.utils;

import com.knits.sboot.common.beans.UserDto;
import com.knits.sboot.common.model.User;

public class BeanUtils {

	
	public static User dto2Model(UserDto dto){
		User user = new User();
		String [] tokens= dto.getName().split("[ ]");
		user.setFirstName(tokens[0]);
		user.setLastName(tokens[1]);
		user.setEmail(dto.getEmail());
		user.setTelephone(dto.getTelephone());
		user.setCity(dto.getCity());
		return user;
	}
	
	public static UserDto model2Dto(User model){
		UserDto dto = new UserDto();
		dto.setName(model.getFirstName()+" "+model.getLastName());
		dto.setEmail(model.getEmail());
		dto.setTelephone(model.getTelephone());
		dto.setCity(model.getCity());
		return dto;
	}
}
