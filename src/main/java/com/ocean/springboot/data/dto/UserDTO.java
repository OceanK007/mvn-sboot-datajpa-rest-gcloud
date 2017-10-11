package com.ocean.springboot.data.dto;


public class UserDTO extends GenericMasterDTO
{
	private String username;
	private String password;
	private RoleDTO roleDTO;
	private UserDetailDTO userDetailDTO;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public RoleDTO getRoleDTO() {
		return roleDTO;
	}
	public void setRoleDTO(RoleDTO roleDTO) {
		this.roleDTO = roleDTO;
	}
	public UserDetailDTO getUserDetailDTO() {
		return userDetailDTO;
	}
	public void setUserDetailDTO(UserDetailDTO userDetailDTO) {
		this.userDetailDTO = userDetailDTO;
	}
}
