package com.knits.sboot.data.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.knits.sboot.common.data.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	List<User> findByLastName(String lastName);
}
