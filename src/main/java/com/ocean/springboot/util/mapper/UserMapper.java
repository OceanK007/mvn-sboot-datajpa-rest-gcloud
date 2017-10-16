package com.ocean.springboot.util.mapper;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ocean.springboot.data.dto.RoleDTO;
import com.ocean.springboot.data.dto.UserDTO;
import com.ocean.springboot.data.dto.UserDetailDTO;
import com.ocean.springboot.data.entity.Role;
import com.ocean.springboot.data.entity.User;
import com.ocean.springboot.data.entity.UserDetail;

@Component
public class UserMapper extends AbstractMapper<User, UserDTO> 
{
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private UserDetailMapper userDetailMapper;
	
	@Override
	public UserDTO mapToDTO(User sourceEntity, UserDTO targetDTO) 
	{
		targetDTO.setId(sourceEntity.getId());
		targetDTO.setUsername(sourceEntity.getUsername());
		targetDTO.setPassword(sourceEntity.getPassword());
		targetDTO.setRoleDTO(roleMapper.mapToDTO(sourceEntity.getRole(), new RoleDTO()));
		targetDTO.setUserDetailDTO(userDetailMapper.mapToDTO(sourceEntity.getUserDetail(), new UserDetailDTO()));
		
		return targetDTO;
	}

	@Override
	public User mapToEntity(UserDTO sourceDTO, User targetEntity) 
	{
		targetEntity.setZoneId(sourceDTO.getZoneId());
		targetEntity.setDateCreated(new DateTime(sourceDTO.getDateCreated(), DateTimeZone.UTC));
		targetEntity.setDateModified(new DateTime(sourceDTO.getDateModified(), DateTimeZone.UTC));
		targetEntity.setUsername(sourceDTO.getUsername());
		targetEntity.setPassword(sourceDTO.getPassword());
		targetEntity.setRole(roleMapper.mapToEntity(sourceDTO.getRoleDTO(), new Role()));
		targetEntity.setUserDetail(userDetailMapper.mapToEntity(sourceDTO.getUserDetailDTO(), new UserDetail()));
		
		return targetEntity;
	}
	
	public UserDTO mapToDTOWithoutMappings(User sourceEntity, UserDTO targetDTO)
	{
		targetDTO.setId(sourceEntity.getId());
		targetDTO.setUsername(sourceEntity.getUsername());
		targetDTO.setPassword(sourceEntity.getPassword());
		return targetDTO;
	}
}
