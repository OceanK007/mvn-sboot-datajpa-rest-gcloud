package com.ocean.springboot.mongo.service;

import java.util.List;

import com.ocean.springboot.data.dto.UserDTO;

public interface UserService 
{	
	List<UserDTO> getUserList();
	UserDTO findByUsername(String username);
	List<UserDTO> findByAddress(String address);
	UserDTO createUser(UserDTO userDTO);
	UserDTO updateUser(UserDTO userDTO);
	void deleteUserById(String userMongoId);
}