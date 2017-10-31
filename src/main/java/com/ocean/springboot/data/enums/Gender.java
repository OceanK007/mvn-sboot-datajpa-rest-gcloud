package com.ocean.springboot.data.enums;

import java.util.HashMap;

public enum Gender 
{
	M("Male"),
	F("Female"),
	O("Others");
	
	private String value;
	private static HashMap<String, Gender> valueLookup = new HashMap<String, Gender>();
	
	static
	{
		for(Gender gender: values())
		{
			valueLookup.put(gender.getValue().toLowerCase(), gender);
		}
	}
	
	Gender(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public static Gender getByValue(String value)
	{
		Gender gender = null;
		if(value != null)
		{
			gender = valueLookup.get(value.toLowerCase());
		}
		return gender;
	}
}