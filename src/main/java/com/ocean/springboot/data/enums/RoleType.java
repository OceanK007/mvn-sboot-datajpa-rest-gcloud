package com.ocean.springboot.data.enums;

import java.util.HashMap;

public enum RoleType 
{
	ADMIN("Admin"),
	USER("User"),
	EDITOR("Editor");
	
	private String name;
	private static HashMap<String, RoleType> nameLookup = new HashMap<String, RoleType>();
	
	static
	{
		for(RoleType roleType: values())
		{
			nameLookup.put(roleType.getName().toLowerCase(), roleType);
		}
	}
	
	RoleType(String name) 
	{
		this.name=name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public static RoleType getByName(String name)
	{
		RoleType roleType = null;
		if(name != null)
		{
			roleType = nameLookup.get(name.toLowerCase());
		}
		return roleType;
	}
}