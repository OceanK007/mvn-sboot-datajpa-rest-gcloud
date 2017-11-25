package com.ocean.springboot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ocean.springboot.data.dto.UserDTO;

public interface UserService 
{	
	UserDTO createUser(UserDTO userDTO, String zoneId);
	UserDTO updateUser(UserDTO userDTO, String zoneId);
	Page<UserDTO> getUserByPage(Pageable pageable);
	UserDTO getUserById(Long userId);
	Page<UserDTO> searchUserByPageCriteria(UserDTO userDTO, Pageable pageable);
}
