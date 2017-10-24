package com.ocean.springboot.util.validator;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.ocean.springboot.data.dto.UserDTO;
import com.ocean.springboot.util.constant.FieldConstant;
import com.ocean.springboot.util.constant.MessageConstant;

@Component
public class UserValidator extends AbstractMasterValidator<UserDTO>
{
	@Override
	public Map<String, String> validate(UserDTO sourceDTO) 
	{
		Map<String, String> validationErrors = new HashMap<String, String>();
		
		if(StringUtils.isBlank(sourceDTO.getUsername()))	// Checks for null, blank string (after trimming)
		{
			validationErrors.put(FieldConstant.FIELD_USER_USERNAME, MessageConstant.MESSAGE_USER_USERNAME_BLANK);
		}
		if(StringUtils.isBlank(sourceDTO.getPassword()))
		{
			validationErrors.put(FieldConstant.FIELD_USER_PASSWORD, MessageConstant.MESSAGE_USER_PASSWORD_BLANK);
		}
		
		return validationErrors;
	}
}