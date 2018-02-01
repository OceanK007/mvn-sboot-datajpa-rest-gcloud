package com.ocean.springboot.util.builder;

import com.ocean.springboot.data.dto.RoleDTO;
import com.ocean.springboot.data.dto.UserDTO;
import com.ocean.springboot.data.dto.UserDetailDTO;
import com.ocean.springboot.data.enums.Gender;
import com.ocean.springboot.data.enums.RoleType;

public class UserDTOBuilder 
{
	public UserDTO buildDTOForEndPoint()
	{
		UserDTO userDTO = new UserDTO();
		userDTO.setId(1L);
		userDTO.setUsername("Ocean");
		userDTO.setPassword("Life");
		
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setId(1L);
		roleDTO.setRoleType(RoleType.ADMIN.getValue());
		
		UserDetailDTO userDetailDTO = new UserDetailDTO();
		userDetailDTO.setId(1L);
		userDetailDTO.setAddress("Chasing my bliss");
		userDetailDTO.setEmail("OceanK007@gmail.com");
		userDetailDTO.setFirstName("Ocean");
		userDetailDTO.setLastName("Life");
		userDetailDTO.setGender(Gender.M.getValue());
		userDetailDTO.setZip("110094");
		
		userDTO.setRoleDTO(roleDTO);
		userDTO.setUserDetailDTO(userDetailDTO);
		
		return userDTO;
	}
}
