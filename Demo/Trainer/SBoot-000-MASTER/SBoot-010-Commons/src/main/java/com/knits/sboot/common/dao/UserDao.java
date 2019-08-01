package com.knits.sboot.common.dao;

import java.util.List;

import com.knits.sboot.common.model.User;

public interface UserDao {

	Long save(User userEntity);

	List<User> findByCity(String city);

}
