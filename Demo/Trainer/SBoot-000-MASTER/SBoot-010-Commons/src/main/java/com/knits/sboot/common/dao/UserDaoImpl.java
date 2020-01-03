package com.knits.sboot.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.knits.sboot.common.model.User;

@Repository
public class UserDaoImpl implements UserDao{

	@Override
	public Long save(User userEntity) {
		return null;
	}

	@Override
	public List<User> findByCity(String city) {
		return null;
	}

}
