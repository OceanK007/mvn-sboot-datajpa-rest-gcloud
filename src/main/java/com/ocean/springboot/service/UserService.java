package com.ocean.springboot.service;

import com.ocean.springboot.data.dto.UserDTO;

public interface UserService 
{
	UserDTO saveUser(UserDTO userDTO);
}
