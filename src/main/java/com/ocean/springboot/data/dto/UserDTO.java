package com.ocean.springboot.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)	// To remove null fields from json
public class UserDTO extends AbstractMasterDTO
{
	private String mongoId;
	private String username;
	private String password;
	private RoleDTO roleDTO;
	private UserDetailDTO userDetailDTO;
	
	public String getMongoId() {
		return mongoId;
	}
	public void setMongoId(String mongoId) {
		this.mongoId = mongoId;
	}
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
