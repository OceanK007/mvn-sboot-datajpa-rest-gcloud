package com.ocean.springboot.data.mongo.entity;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ocean.springboot.data.enums.RoleType;

@Document(collection="role")
public class Role implements Serializable
{
	private static final long serialVersionUID = 4164320341535594845L;
	
	// It will store it as a string in mongo, and automatically convert when you read it out.
	private RoleType roleType;

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}
}