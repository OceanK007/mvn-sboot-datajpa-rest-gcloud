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

@Service
public class UserServiceImpl implements UserService
{
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	/** ModelMapper: To map DTO to Entity and vice-versa | etc **/
	private static ModelMapper modelMapper = ModelMapperHelper.modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDTO saveUser(UserDTO userDTO) 
	{
		//Role role = modelMapper.map(userDTO.getRoleDTO(), Role.class);
		User user = modelMapper.map(userDTO, User.class);
		userRepository.save(user);
		logger.info("User saved with ID: "+user.getId());
		userDTO.setId(user.getId());
		return userDTO;
	}

	@Override
	public Page<UserDTO> getUserByPage(int page, int pageSize) 
	{
		Pageable pageable = new PageRequest(page-1, pageSize);
		logger.info("Retrieving users with page: "+page+" | pageSize: "+pageSize);
		Page<User> pageUser = userRepository.findUserByPage(pageable);
		
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		pageUser.getContent().forEach(user -> userDTOList.add(modelMapper.map(user, UserDTO.class)));
		
		Page<UserDTO> pageUserDTO = new PageImpl<>(userDTOList, pageable, pageUser.getTotalElements());
		
		return pageUserDTO;
	}
}
