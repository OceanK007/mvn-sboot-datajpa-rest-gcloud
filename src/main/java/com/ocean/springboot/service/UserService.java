package com.ocean.springboot.service;

import org.springframework.data.domain.Page;

import com.ocean.springboot.config.exception.ApplicationGenericException;
import com.ocean.springboot.data.dto.UserDTO;

public interface UserService 
{	
	UserDTO createUser(UserDTO userDTO, String zoneId);
	UserDTO updateUser(UserDTO userDTO, String zoneId);
	Page<UserDTO> getUserByPage(int page, int pageSize);
	UserDTO getUserById(Long userId);
}
