package com.ocean.springboot.data.enums;

public enum ProfileType 
{
	ADMIN("Admin"),
	USER("User"),
	EDITOR("Editor");
	
	private String name;
	
	ProfileType(String name) 
	{
		this.name=name;
	}
	
	public String getName(String name)
	{
		return name;
	}
}