package com.ocean.springboot.mockito.test;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 	Use this if you don't want to use @RunWith(MockitoJUnitRunner.class) annotation
 	
	@Before
    public void setUp() throws Exception 
	{
        MockitoAnnotations.initMocks(this);
    }
**/
@RunWith(MockitoJUnitRunner.class)															// Applicable for Junit 4.4 and higher	// More preferable
public class MockitoInjectMocksAnnotation 
{
	/*@Before
    public void setUp() throws Exception 
	{
        MockitoAnnotations.initMocks(this);
    }*/
	 
	/**
		We are doing field injection here, so @Mock will first try to resolve the field by its type and mock name will be the name of field name by default if you have not provided "name" attribute.
		Note: If you have multiple field with same type, then try to use @Mock(name="fieldName"), else @Mock will get confused (But it's not working!!!!!).
    **/
	@Mock
	private Map<String, String> wordMapMock;												// All methods belonged to this wordMap have been mocked.
	
	@InjectMocks
	private MyDictionary myDictionary = new MyDictionary();									// wordMap will automatically be injected in myDictionary
	
	@Test
	public void withInjectMocksAnnotation()
	{
		myDictionary.add("testWord", "testMeaning");										// add() will be called as real method of myDictionary, but .put() method inside that add() method has been mocked.
		
		System.out.println("wordMapMock.size(): "+wordMapMock.size());						// Will print 0, since size() method of wordMap has been mocked.
		
		System.out.println("myDictionary.getSize(): "+myDictionary.getSize());  			// but Will print 0, since size() method of wordMap has been mocked. getSize() will be called as real method of myDictionary.
		
		Mockito.verify(wordMapMock).put("testWord", "testMeaning");							// Since we have mocked wordMap, so we are verifying that put() method has been called once at least when we called myDictionary.add("testWord", "testMeaning");
		//Mockito.verify(wordMapMock).put("testWord2", "testMeaning2");						// Will throw exception since arguments passed in mock are case-sensitive 
		
		Mockito.verify(wordMapMock).put(Mockito.anyString(), Mockito.anyString());	
		
		//Mockito.verify(myDictionary).add(Mockito.anyString(), Mockito.anyString());		// It will throw exception since we have mocked wordMap not myDictionary, we have injected mock in myDictionary object
		
		Mockito.when(wordMapMock.get("testWord")).thenReturn("testMeaning");				// when: Enables stubbing methods. Use it when you want the mock to return particular value when particular method is called. Simply "When the x method is called then return y".
		//Mockito.doReturn("testMeaning").when(wordMapMock).get("testWord");				// Alternative way for stubbing methods
		 
		// Since we have stubbed methods of wordMap, so when myDictionary.getMeaning() will be called, it will internally call wordMap.get() method then the stubbed method will be executed and predefined result will be fetched.
	    Assert.assertEquals("testMeaning", myDictionary.getMeaning("testWord"));			// Asserts that two objects are equal. If they are not, an exception is thrown
	}
	
	@Test
	public void throwExceptionOnMethodCallCheck()
	{
		Mockito.doThrow(new RuntimeException()).when(wordMapMock).size();					// doThrow: Use doThrow() when you want to stub the void method with an exception.
		wordMapMock.size();																	// Now calling method
	}
}