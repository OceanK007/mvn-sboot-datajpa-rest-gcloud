package com.ocean.springboot.api.endpoint;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ocean.springboot.data.dto.UserDTO;
import com.ocean.springboot.service.UserService;
import com.ocean.springboot.util.builder.UserDTOBuilder;

/**
	Use this if you don't want to use @RunWith(MockitoJUnitRunner.class) annotation
	
	@Before
	public void setUp() throws Exception 
	{
	    MockitoAnnotations.initMocks(this);
	}
**/
//@RunWith(SpringRunner.class)	// SpringRunner extends SpringJUnit4ClassRunner which extends BlockJUnit4ClassRunner
@RunWith(MockitoJUnitRunner.class)	// Applicable for Junit 4.4 and higher	// More preferable
public class UserApiTest 
{
	/*@Before
    public void setUp() throws Exception 
	{
        MockitoAnnotations.initMocks(this);
    }*/
	
	@InjectMocks
	private UserApi userApiMock;																								// userServiceMock will automatically be injected in userApiMock
	
	@Mock
	private UserService userServiceMock;																						// All methods belonged to this UserService have been mocked.
	
	@Test
	public void createUserTest() throws Exception
	{
		UserDTO userDTO = new UserDTOBuilder().buildDTOForEndPoint();
		
		Mockito.when(userServiceMock.createUser((UserDTO) Matchers.anyObject(), Matchers.anyString())).thenReturn(userDTO);		// when: Enables stubbing methods. Use it when you want the mock to return particular value when particular method is called. Simply "When the x method is called then return y".
		//Mockito.doReturn(userDTO).when(userServiceMock).createUser((UserDTO) Matchers.anyObject(), Matchers.anyString());		// Alternative way for stubbing methods
		
		UserDTO returnedUserDTO = userApiMock.createUser(userDTO, "UTC");														// createUser() will be called as real method of UserApi, but userService.createUser() method inside that createUser() method has been mocked.
		
		Mockito.verify(userServiceMock).createUser((UserDTO) Matchers.anyObject(), Matchers.anyString());						// Since we have mocked userServiceMock, so we are verifying that createUser() method has been called once at least when we called userApiMock.createUser(userDTO, "UTC");
		
		// Since we have stubbed methods of UserService, so when userApiMock.createUser will be called, it will internally call userService.createUser method then the stubbed method will be executed and predefined result will be fetched.
		Assert.assertNotNull(returnedUserDTO);																					// Asserts that the object is not null. If it is, an exception is thrown
		Assert.assertEquals(returnedUserDTO.getId(), userDTO.getId());															// Asserts that two objects are equal. If they are not, an exception is thrown
	}
	
	@Test
	public void updateUserTest() throws Exception
	{
		UserDTO userDTO = new UserDTOBuilder().buildDTOForEndPoint();
		Mockito.when(userServiceMock.updateUser((UserDTO) Matchers.anyObject(), Matchers.anyString())).thenReturn(userDTO);
		UserDTO returnedUserDTO = userApiMock.updateUser(userDTO, "UTC");	
		Mockito.verify(userServiceMock).updateUser((UserDTO) Matchers.anyObject(), Matchers.anyString());	
		Assert.assertNotNull(returnedUserDTO);	
		Assert.assertEquals(returnedUserDTO.getId(), userDTO.getId());	
	}
	

	@Test
	public void getUserByIdTest() throws Exception
	{
		UserDTO userDTO = new UserDTOBuilder().buildDTOForEndPoint();
		Mockito.when(userServiceMock.getUserById(Matchers.anyLong())).thenReturn(userDTO);
		UserDTO returnedUserDTO = userApiMock.getUserById(userDTO.getId());
		Mockito.verify(userServiceMock).getUserById(Matchers.anyLong());
		Assert.assertNotNull(returnedUserDTO);
		Assert.assertEquals(returnedUserDTO.getId(), userDTO.getId());	
	}
	
	@Test
	public void getUserList() throws Exception
	{
		Page<UserDTO> userDTOPage = new UserDTOBuilder().buildUserDTOPageForEndPoint();
		Mockito.when(userServiceMock.getUserByPage((Pageable) Matchers.anyObject())).thenReturn(userDTOPage);
		Page<UserDTO> returnedUserDTOPage = userApiMock.getUserByPage((Pageable) Matchers.anyObject());
		Mockito.verify(userServiceMock).getUserByPage((Pageable) Matchers.anyObject());
		Assert.assertNotNull(returnedUserDTOPage);
		Assert.assertEquals(userDTOPage.getContent().size(), returnedUserDTOPage.getContent().size());	
	}
	
	@Test
	public void getUserBriefList() throws Exception
	{
		Page<UserDTO> userDTOPage = new UserDTOBuilder().buildUserDTOPageForEndPoint();
		Mockito.when(userServiceMock.getUserBriefByPage((Pageable) Matchers.anyObject())).thenReturn(userDTOPage);
		Page<UserDTO> returnedUserDTOPage = userApiMock.getUserBrief((Pageable) Matchers.anyObject());
		Mockito.verify(userServiceMock).getUserBriefByPage((Pageable) Matchers.anyObject());
		Assert.assertNotNull(returnedUserDTOPage);
		Assert.assertEquals(userDTOPage.getContent().size(), returnedUserDTOPage.getContent().size());	
	}
}