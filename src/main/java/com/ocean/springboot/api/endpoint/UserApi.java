package com.ocean.springboot.api.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ocean.springboot.data.dto.UserDTO;
import com.ocean.springboot.service.UserService;
import com.ocean.springboot.util.constant.ApiConstant;

@RestController
@RequestMapping(ApiConstant.USER_API)
public class UserApi 
{
	private static final Logger logger = LoggerFactory.getLogger(UserApi.class);
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public UserDTO createUser(UserDTO userDTO)
	{
		return userService.saveUser(userDTO);
	}
}
