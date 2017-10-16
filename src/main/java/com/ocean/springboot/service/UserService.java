package com.ocean.springboot.service;

import org.springframework.data.domain.Page;

import com.ocean.springboot.data.dto.UserDTO;

public interface UserService 
{
	UserDTO saveUser(UserDTO userDTO);
	Page<UserDTO> getUserByPage(int page, int pageSize);
}
