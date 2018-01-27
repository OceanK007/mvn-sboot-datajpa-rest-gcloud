package com.ocean.springboot.mongo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocean.springboot.data.dto.RoleDTO;
import com.ocean.springboot.data.dto.UserDTO;
import com.ocean.springboot.data.dto.UserDetailDTO;
import com.ocean.springboot.data.enums.Gender;
import com.ocean.springboot.data.enums.RoleType;
import com.ocean.springboot.data.mongo.entity.Role;
import com.ocean.springboot.data.mongo.entity.User;
import com.ocean.springboot.data.mongo.entity.UserDetail;
import com.ocean.springboot.data.mongo.repository.UserRepository;
import com.ocean.springboot.mongo.service.UserService;

//If you don't give name attribute then spring will try to create bean with name userServiceImpl which already exist since we have another UserServiceImpl file and it will throw exception.
@Service(value="userServiceMongoImpl")
public class UserServiceImpl implements UserService
{
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<UserDTO> getUserList() 
	{
		List<User> userList = userRepository.findAll();
		List<UserDTO> userDTOList = new ArrayList<>();
		
		if(userList != null && userList.size() > 0)
		{
			userList.forEach(user -> userDTOList.add(convertToDTO(user, new UserDTO())));
		}
		
		return userDTOList;
	}
	
	@Override
	public UserDTO findByUsername(String username) 
	{
		User user = userRepository.findByUsername(username);
		UserDTO userDTO = new UserDTO();
		if(user !=null)
		{
			userDTO = convertToDTO(user, userDTO);
		}
		
		return userDTO;
	}


	@Override
	public List<UserDTO> findByAddress(String address) 
	{
		List<User> userList = userRepository.findByAddress(address);
		List<UserDTO> userDTOList = new ArrayList<>();
		
		if(userList != null && userList.size() > 0)
		{
			userList.forEach(user -> userDTOList.add(convertToDTO(user, new UserDTO())));
		}
		
		return userDTOList;
	}
	
	@Override
	public UserDTO createUser(UserDTO userDTO) 
	{
		User user = convertToEntity(userDTO, new User());
		userRepository.save(user);
		userDTO.setMongoId(user.getMongoId());
		
		return userDTO;
	}
	
	@Override
	public UserDTO updateUser(UserDTO userDTO) 
	{
		User user = convertToEntity(userDTO, new User());
		userRepository.save(user);
		return userDTO;
	}
	
	@Override
	public void deleteUserById(String userMongoId) 
	{
		userRepository.delete(userMongoId);
	}
	
	public UserDTO convertToDTO(User source, UserDTO target)
	{
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setRoleType(source.getRole().getRoleType().getValue());
		
		UserDetailDTO userDetailDTO = new UserDetailDTO();
		userDetailDTO.setAddress(source.getUserDetail().getAddress());
		userDetailDTO.setEmail(source.getUserDetail().getEmail());
		userDetailDTO.setFirstName(source.getUserDetail().getFirstName());
		userDetailDTO.setGender(source.getUserDetail().getGender().getValue());
		userDetailDTO.setLastName(source.getUserDetail().getLastName());
		userDetailDTO.setMiddleName(source.getUserDetail().getMiddleName());
		userDetailDTO.setZip(source.getUserDetail().getZip());
		
		target.setMongoId(source.getMongoId());
		target.setUsername(source.getUsername());
		target.setPassword(source.getPassword());
		target.setRoleDTO(roleDTO);
		target.setUserDetailDTO(userDetailDTO);
		
		return target;
	}
	
	public User convertToEntity(UserDTO source, User target)
	{
		Role role = new Role();
		role.setRoleType(RoleType.getByValue(source.getRoleDTO().getRoleType()));
		
		UserDetail userDetail = new UserDetail();
		userDetail.setAddress(source.getUserDetailDTO().getAddress());
		userDetail.setEmail(source.getUserDetailDTO().getEmail());
		userDetail.setFirstName(source.getUserDetailDTO().getFirstName());
		userDetail.setGender(Gender.getByValue(source.getUserDetailDTO().getGender()));
		userDetail.setLastName(source.getUserDetailDTO().getLastName());
		userDetail.setMiddleName(source.getUserDetailDTO().getMiddleName());
		userDetail.setZip(source.getUserDetailDTO().getZip());
		
		target.setMongoId(source.getMongoId());
		target.setUsername(source.getUsername());
		target.setPassword(source.getPassword());
		target.setRole(role);
		target.setUserDetail(userDetail);
		
		return target;
	}
}