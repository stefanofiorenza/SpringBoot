package com.knits.sboot.common.data.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String firstName;
	private String lastName;
	private String email;
	private String telephone;
	private String city;
 
	
	public User () {}
	
	public User (String firstName , String lastName) {
		this.firstName= firstName;
		this.lastName=lastName;
	}
}
