package com.ocean.springboot.data.dto;

public class RoleDTO extends GenericMasterDTO
{
	private String roleType;

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
}
