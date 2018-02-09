package com.ocean.springboot.powermockito.test;

import java.util.HashMap;
import java.util.Map;

public class MyDictionaryStatic 
{
	Map<String, String> wordMap;		// If you make this as static as well then Mockito won't work on static 
	
	//Map<String, String> tempForTest;		// Unable to mock if you define another field with same data type
	 
	public MyDictionaryStatic() 
	{
		System.out.println("Creating object of wordMap");
	    wordMap = new HashMap<String, String>();
	}
	
	// START: Static Methods //
	public static void addStatic(String word, String meaning) 
	{
		System.out.println("Static: Adding element in wordMap");
	}
	
	public static String getMeaningStatic(String word) 
	{
		System.out.println("Static: Fetching element of wordMap");
	    return "Static: Fetching element of wordMap";
	}
	// END: Static Methods //
	
	public void add(String word, String meaning) 
	{
		System.out.println("Static: Adding element in wordMap");
	}
	
	public String getMeaning(String word) 
	{
		System.out.println("Static: Fetching element of wordMap");
	    return "Static: Fetching element of wordMap";
	}
	
	public Integer getSize()
	{
		System.out.println("Fetching size of wordMap");
		return wordMap.size();
	}
	
	public static void test()
	{
		System.out.println("Test Method");
	}
}