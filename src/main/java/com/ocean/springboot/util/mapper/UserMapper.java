package com.ocean.springboot.util.mapper;

import org.springframework.stereotype.Component;

import com.ocean.springboot.data.dto.UserDTO;
import com.ocean.springboot.data.entity.User;

@Component
public class UserMapper extends AbstractMapper<User, UserDTO> 
{
	@Override
	protected UserDTO mapToDTO(User sourceAbstractMasterEntity, UserDTO targetAbstractMasterDTO) 
	{
		return null;
	}

	@Override
	protected User mapToEntity(UserDTO sourceAbstractMasterEntity, User targetAbstractMasterEntity) 
	{
		return null;
	}
}
