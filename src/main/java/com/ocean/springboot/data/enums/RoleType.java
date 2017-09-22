package com.ocean.springboot.data.enums;

public enum RoleType 
{
	ADMIN("Admin"),
	USER("User"),
	EDITOR("Editor");
	
	private String name;
	
	RoleType(String name) 
	{
		this.name=name;
	}
	
	public String getName(String name)
	{
		return name;
	}
}