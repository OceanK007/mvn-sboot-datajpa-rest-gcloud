package com.ocean.springboot.api.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocean.springboot.data.dto.UserDTO;
import com.ocean.springboot.data.entity.User;
import com.ocean.springboot.util.constant.ApiConstant;

@RestController
@RequestMapping(ApiConstant.USER_API)
public class UserApi 
{
	private static final Logger logger = LoggerFactory.getLogger(UserApi.class);
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public UserDTO createUser(UserDTO userDTO)
	{
		User user = objectMapper.convertValue(userDTO, User.class);	
		return null;
	}
}
