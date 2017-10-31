package com.ocean.springboot.data.enums;

import java.util.HashMap;

public enum RoleType 
{
	ADMIN("Admin"),
	USER("User"),
	EDITOR("Editor");
	
	private String value;
	private static HashMap<String, RoleType> valueLookup = new HashMap<String, RoleType>();
	
	static
	{
		for(RoleType roleType: values())
		{
			valueLookup.put(roleType.getValue().toLowerCase(), roleType);
		}
	}
	
	RoleType(String value) 
	{
		this.value=value;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public static RoleType getByValue(String value)
	{
		RoleType roleType = null;
		if(value != null)
		{
			roleType = valueLookup.get(value.toLowerCase());
		}
		return roleType;
	}
}