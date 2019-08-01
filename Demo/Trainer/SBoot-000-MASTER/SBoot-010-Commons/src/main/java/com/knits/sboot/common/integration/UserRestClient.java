package com.knits.sboot.common.integration;

import org.springframework.stereotype.Component;

import com.knits.sboot.common.beans.UserDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserRestClient {

	public void sendUserToExternalRestService(UserDto user){
		log.info("Send to External Service through Rest Api");
	}
}
