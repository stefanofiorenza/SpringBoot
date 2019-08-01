package com.knits.sboot.common.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knits.sboot.common.beans.UserDto;
import com.knits.sboot.common.dao.UserDao;
import com.knits.sboot.common.integration.UserJmsClient;
import com.knits.sboot.common.integration.UserRestClient;
import com.knits.sboot.common.model.User;
import com.knits.sboot.common.utils.BeanUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserJmsClient jmsClient;
	
	@Autowired
	private UserRestClient userRestClient;

	@Autowired
	private UserDao userDao;
	
	@Override
	public Long save(UserDto user) {

		log.info("Saving User {} ...",user.toString());
		
		if(user.getCity().equals("Tallinn")){
			jmsClient.sendUserToJmsQueue(user);
			log.info("User {} Sent To JmsQueue ",user.toString());
		}
		
		userRestClient.sendUserToExternalRestService(user);
		log.info("User {} Sent To ExternalRestService ",user.toString());
		
		User userEntity = BeanUtils.dto2Model(user);
		Long newUserId = userDao.save(userEntity);
		log.info("User {} Saved into DB ",user.toString());
		return newUserId;
	}

	@Override
	public List<UserDto> findUsersByCity(String city) {
		log.info("Search Users by City {} ",city);
		List<User> usersFromDd =userDao.findByCity(city);		
		List<UserDto> usersAsDtos =new ArrayList<UserDto>();
		usersFromDd.forEach(user -> usersAsDtos.add(BeanUtils.model2Dto(user)));
		return usersAsDtos;
	}

	@Override
	public void updateUser(UserDto user) {
		log.info("Update User {} ",user.toString());
		
		userRestClient.sendUserToExternalRestService(user);
		log.info("User {} Sent To ExternalRestService ",user.toString());
		
		User userEntity = BeanUtils.dto2Model(user);
		userDao.save(userEntity);
		log.info("User {} Updated into DB ",user.toString());
		
	}

}
