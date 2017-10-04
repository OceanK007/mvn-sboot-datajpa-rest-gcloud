package com.ocean.springboot.data.dto;


public class UserDTO extends GenericMasterDTO
{
	private String username;
	private String password;
	private String role;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public UserDetailDTO getUserDetailDTO() {
		return userDetailDTO;
	}
	public void setUserDetailDTO(UserDetailDTO userDetailDTO) {
		this.userDetailDTO = userDetailDTO;
	}
}
