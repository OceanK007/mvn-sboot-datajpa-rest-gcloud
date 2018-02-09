package com.ocean.springboot.util.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.ocean.springboot.data.dto.RoleDTO;
import com.ocean.springboot.data.dto.UserDTO;
import com.ocean.springboot.data.dto.UserDetailDTO;
import com.ocean.springboot.data.enums.Gender;
import com.ocean.springboot.data.enums.RoleType;

public class UserDTOBuilder 
{
	private final int PAGE_NUMBER = 1;
	private final int PAGE_SIZE = 5;
	private final String SORTING_FIELD_NAME_ID = "id";
	 
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
	
	public Page<UserDTO> buildUserDTOPageForEndPoint()
	{
		List<UserDTO> userList = new ArrayList<UserDTO>();
		userList.add(buildDTOForEndPoint());
		
		Sort sort = new Sort(Sort.Direction.ASC, SORTING_FIELD_NAME_ID);
		PageRequest pageRequest = new PageRequest(PAGE_NUMBER, PAGE_SIZE, sort);
		
		Page<UserDTO> userDTOPage = new PageBuilder<UserDTO>()
											.elements(userList)
											.pageRequest(pageRequest)
											.totalElements(0)
											.build();
		
		return userDTOPage;
	}
}
