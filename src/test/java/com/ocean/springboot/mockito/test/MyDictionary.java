package com.ocean.springboot.mockito.test;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary 
{
	Map<String, String> wordMap;
	 
	public MyDictionary() 
	{
		System.out.println("Creating object of wordMap");
	    wordMap = new HashMap<String, String>();
	}
	
	public void add(final String word, final String meaning) 
	{
		System.out.println("Adding element in wordMap");
	    wordMap.put(word, meaning);
	}
	
	public String getMeaning(final String word) 
	{
		System.out.println("Fetching element of wordMap");
	    return wordMap.get(word);
	}
	
	public Integer getSize()
	{
		System.out.println("Fetching size of wordMap");
		return wordMap.size();
	}
}