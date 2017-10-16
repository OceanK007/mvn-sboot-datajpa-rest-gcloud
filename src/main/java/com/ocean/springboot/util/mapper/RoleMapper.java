package com.ocean.springboot.util.mapper;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Component;

import com.ocean.springboot.data.dto.RoleDTO;
import com.ocean.springboot.data.entity.Role;
import com.ocean.springboot.data.enums.RoleType;

@Component
public class RoleMapper extends AbstractMapper<Role, RoleDTO> 
{
	@Override
	public RoleDTO mapToDTO(Role sourceEntity, RoleDTO targetDTO) 
	{
		targetDTO.setId(sourceEntity.getId());
		targetDTO.setZoneId(sourceEntity.getZoneId());
		targetDTO.setRoleType(sourceEntity.getRoleType().getName());
		
		return targetDTO;
	}

	@Override
	public Role mapToEntity(RoleDTO sourceDTO, Role targetEntity) 
	{
		targetEntity.setRoleType(RoleType.getByName(sourceDTO.getRoleType()));
		targetEntity.setZoneId(sourceDTO.getZoneId());
		targetEntity.setDateCreated(new DateTime(sourceDTO.getDateCreated(), DateTimeZone.UTC));
		targetEntity.setDateModified(new DateTime(sourceDTO.getDateModified(), DateTimeZone.UTC));

		return targetEntity;
	}
}
