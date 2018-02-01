package com.ocean.springboot.api.endpoint;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ocean.springboot.data.dto.UserDTO;
import com.ocean.springboot.service.UserService;
import com.ocean.springboot.util.builder.UserDTOBuilder;

//@RunWith(SpringRunner.class)	// SpringRunner extends SpringJUnit4ClassRunner which extends BlockJUnit4ClassRunner
@RunWith(MockitoJUnitRunner.class)	// Applicable for Junit 4.4 and higher	// More preferable
public class UserApiTest 
{
	@InjectMocks
	private UserApi userApiMock;
	
	@Mock
	private UserService userServiceMock;
	
	@Test
	public void createUserTest() throws Exception
	{
		UserDTO userDTO = new UserDTOBuilder().buildDTOForEndPoint();
		
		Mockito.when(userServiceMock.createUser((UserDTO) Matchers.anyObject(), Matchers.anyString())).thenReturn(userDTO);
		
		UserDTO returnedUserDTO = userApiMock.createUser(userDTO, "UTC");
		
		Assert.assertNotNull(returnedUserDTO);
		Assert.assertEquals(returnedUserDTO.getId(), userDTO.getId());
		
		Mockito.verify(userServiceMock).createUser((UserDTO) Matchers.anyObject(), Matchers.anyString());
	}
}
