package com.ocean.springboot.util.mapper;

import org.springframework.stereotype.Component;

import com.ocean.springboot.data.dto.RoleDTO;
import com.ocean.springboot.data.entity.Role;

@Component
public class RoleMapper extends AbstractMapper<Role, RoleDTO> 
{
	@Override
	protected RoleDTO mapToDTO(Role sourceAbstractMasterEntity, RoleDTO targetAbstractMasterDTO) 
	{
		return null;
	}

	@Override
	protected Role mapToEntity(RoleDTO sourceAbstractMasterEntity, Role targetAbstractMasterEntity) 
	{
		return null;
	}
}
