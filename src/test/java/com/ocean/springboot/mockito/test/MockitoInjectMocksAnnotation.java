package com.ocean.springboot.mockito.test;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)														// Applicable for Junit 4.4 and higher	// More preferable
public class MockitoInjectMocksAnnotation 
{
	@Mock
	Map<String, String> wordMap;														// All methods belonged to this wordMap have been mocked.
	
	@InjectMocks
	MyDictionary myDictionary = new MyDictionary();										// wordMap will automatically be injected in myDictionary
	
	@Test
	public void withInjectMocksAnnotation()
	{
		myDictionary.add("testWord", "testMeaning");									// add() will be called as real method of myDictionary, but .put() method inside that add() method has been mocked.
		
		System.out.println("wordMap.size(): "+wordMap.size());							// Will print 0, since size() method of wordMap has been mocked.
		
		System.out.println("myDictionary.getSize(): "+myDictionary.getSize());  		// but Will print 0, since size() method of wordMap has been mocked. getSize() will be called as real method of myDictionary.
		
		Mockito.verify(wordMap).put("testWord", "testMeaning");							// Since we have mocked wordMap, so we are verifying that put() method has been called once at least when we called myDictionary.add("testWord", "testMeaning");
		//Mockito.verify(wordMap).put("testWord2", "testMeaning2");						// Will throw exception since arguments passed in mock are case-sensitive 
		
		Mockito.verify(wordMap).put(Mockito.anyString(), Mockito.anyString());	
		
		//Mockito.verify(myDictionary).add(Mockito.anyString(), Mockito.anyString());	// It will throw exception since we have mocked wordMap not myDictionary, we have injected mock in myDictionary object
		
		Mockito.when(wordMap.get("testWord")).thenReturn("testMeaning");				// when: Enables stubbing methods. Use it when you want the mock to return particular value when particular method is called. Simply "When the x method is called then return y".
		//Mockito.doReturn("testMeaning").when(wordMap).get("testWord");				// Alternative way for stubbing methods
		 
		// Since we have stubbed method of wordMap.get(), so when myDictionary.getMeaning() will internally call wordMap.get() method then the stubbed method will be executed and predefined result will be fetched.
	    Assert.assertEquals("testMeaning", myDictionary.getMeaning("testWord"));		// Asserts that two objects are equal. If they are not, an exception is thrown
	}
}