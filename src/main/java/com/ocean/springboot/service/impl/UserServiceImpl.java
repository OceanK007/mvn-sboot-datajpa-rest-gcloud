package com.ocean.springboot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ocean.springboot.data.dto.UserDTO;
import com.ocean.springboot.data.entity.User;
import com.ocean.springboot.data.repository.UserRepository;
import com.ocean.springboot.service.UserService;
import com.ocean.springboot.util.helper.ModelMapperHelper;
import com.ocean.springboot.util.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService
{
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	/** ModelMapper: To map DTO to Entity and vice-versa | etc **/
	private static ModelMapper modelMapper = ModelMapperHelper.modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserDTO createUser(UserDTO userDTO, String zoneId) 
	{
		userDTO.setZoneId(zoneId);
		//User user = modelMapper.map(userDTO, User.class);
		User user = userMapper.mapToEntity(userDTO, new User());
		userRepository.save(user);
		logger.info("User saved with ID: "+user.getId());
		userDTO = userMapper.mapToDTO(user, new UserDTO());
		return userDTO;
	}
	
	@Override
	public UserDTO updateUser(UserDTO userDTO, String zoneId) 
	{
		User user = userRepository.findById(userDTO.getId());
		
		user = userMapper.mapToEntity(userDTO, user);
		user.setId(userDTO.getId());
		user.getUserDetail().setId(userDTO.getUserDetailDTO().getId());
		
		userRepository.save(user);
		logger.info("User updated with ID: "+user.getId());
		
		userDTO = userMapper.mapToDTO(user, new UserDTO());
		return userDTO;
	}

	@Override
	public Page<UserDTO> getUserByPage(int page, int pageSize) 
	{
		Pageable pageable = new PageRequest(page, pageSize);	// Pages are zero indexed, thus providing 0 for page will return the first page.
		
		logger.info("Retrieving users with page: "+page+" | pageSize: "+pageSize);
		Page<User> pageUser = userRepository.findUserByPage(pageable);
		
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		pageUser.getContent().forEach(user -> userDTOList.add(userMapper.mapToDTO(user, new UserDTO())));
		
		Page<UserDTO> pageUserDTO = new PageImpl<>(userDTOList, pageable, pageUser.getTotalElements());
		
		return pageUserDTO;
	}

	@Override
	public UserDTO getUserById(Long userId)
	{
		User user = userRepository.findOne(userId);
		return userMapper.mapToDTO(user, new UserDTO());
	}
}
