package com.ocean.springboot.data.enums;

import java.util.HashMap;

public enum Gender 
{
	M("Male"),
	F("Female"),
	O("Others");
	
	private String name;
	private static HashMap<String, Gender> nameLookup = new HashMap<String, Gender>();
	
	static
	{
		for(Gender gender: values())
		{
			nameLookup.put(gender.getName().toLowerCase(), gender);
		}
	}
	
	Gender(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public static Gender getByName(String name)
	{
		Gender gender = null;
		if(name != null)
		{
			gender = nameLookup.get(name.toLowerCase());
		}
		return gender;
	}
}