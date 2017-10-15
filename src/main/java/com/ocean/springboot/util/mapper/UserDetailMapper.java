package com.ocean.springboot.util.mapper;

import org.springframework.stereotype.Component;

import com.ocean.springboot.data.dto.UserDetailDTO;
import com.ocean.springboot.data.entity.UserDetail;

@Component
public class UserDetailMapper extends AbstractMapper<UserDetail, UserDetailDTO> 
{
	@Override
	protected UserDetailDTO mapToDTO(UserDetail sourceAbstractMasterEntity, UserDetailDTO targetAbstractMasterDTO) 
	{
		return null;
	}

	@Override
	protected UserDetail mapToEntity(UserDetailDTO sourceAbstractMasterEntity, UserDetail targetAbstractMasterEntity) 
	{
		return null;
	}
}
