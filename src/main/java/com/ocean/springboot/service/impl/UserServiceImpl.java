package com.ocean.springboot.service.impl;

import org.modelmapper.ModelMapper;

import com.ocean.springboot.data.dto.UserDTO;
import com.ocean.springboot.data.entity.Role;
import com.ocean.springboot.service.UserService;
import com.ocean.springboot.util.helper.ModelMapperHelper;

public class UserServiceImpl implements UserService
{
	/** Model Mapper: To map DTO to Entity and vice-versa **/
	public static ModelMapper modelMapper = ModelMapperHelper.modelMapper;
	
	@Override
	public UserDTO saveUser(UserDTO userDTO) 
	{
		Role role = modelMapper.map(userDTO.getRoleDTO(), Role.class);
		
		return null;
	}
}
