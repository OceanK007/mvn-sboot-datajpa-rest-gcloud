package com.ocean.springboot.mockito.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
	Use this if you don't want to use @RunWith(MockitoJUnitRunner.class) annotation
	
	@Before
	public void setUp() throws Exception 
	{
	    MockitoAnnotations.initMocks(this);
	}
**/
@RunWith(MockitoJUnitRunner.class)												// Applicable for Junit 4.4 and higher	// More preferable
public class MockitoMockAnnotation 
{
	/*@Before
    public void setUp() throws Exception 
	{
        MockitoAnnotations.initMocks(this);
    }*/
	
	@Mock 
	List<String> mockList;														// mock: Creates mock object of given class or interface. 
	
	@Test
	public void withoutMockAnnotation()
	{
		List<String> mockList = Mockito.mock(ArrayList.class);					// mock: Creates mock object of given class or interface. 
		
		mockList.add("One");													// This won't call the real method of list, so size is still 0 ever after this code
		//mockList.add("Two");
		
		/** verify: Verifies certain behavior happened once. Arguments passed are compared using equals() method, so it's case-sensitive **/
		Mockito.verify(mockList).add("One");									// True since add("One") has called one time 	// Default times is 1 only
		//Mockito.verify(mockList).add("one");									// false since add("one") has not called (Case-sensitivity for parameter)
		//Mockito.verify(mockList, Mockito.times(1)).add("One");				// True since add("One") has called one time
		//Mockito.verify(mockList, Mockito.times(2)).add("One");				// Fails since add("One") has called one time only not two times
		//Mockito.verify(mockList, Mockito.times(1)).add(Mockito.anyString());	// True, since add("One") has called one time
		//Mockito.verify(mockList, Mockito.times(3)).add(Mockito.anyString());	// Fails, since add("One") has called one time only not three times
		
		System.out.println("mockList.size(): "+mockList.size()); 				// Will print 0
		Assert.assertEquals(0, mockList.size());								//  Asserts that two objects are equal. If they are not, an exception is thrown
		
		Mockito.when(mockList.size()).thenReturn(100);							// when: Enables stubbing methods. Use it when you want the mock to return particular value when particular method is called. Simply "When the x method is called then return y".
		//Mockito.doReturn(100).when(mockList).size();							// Alternative way for stubbing methods

		Assert.assertEquals(100, mockList.size());
	}
	
	@Test
	public void withMockAnnotation()
	{
		mockList.add("One");													// This won't call the real method of list, so size is still 0 ever after this code
		//mockList.add("Two");
		
		/** verify: Verifies certain behavior happened once. Arguments passed are compared using equals() method, so it's case-sensitive **/
		Mockito.verify(mockList).add("One");									// True since add("One") has called one time 	// Default times is 1 only
		//Mockito.verify(mockList).add("one");									// false since add("one") has not called (Case-sensitivity for parameter)
		//Mockito.verify(mockList, Mockito.times(1)).add("One");				// True since add("One") has called one time
		//Mockito.verify(mockList, Mockito.times(2)).add("One");				// Fails since add("One") has called one time only not two times
		//Mockito.verify(mockList, Mockito.times(1)).add(Mockito.anyString());	// True, since add("One") has called one time
		//Mockito.verify(mockList, Mockito.times(3)).add(Mockito.anyString());	// Fails, since add("One") has called one time only not three times
		
		System.out.println("mockList.size(): "+mockList.size()); 				// Will print 0
		Assert.assertEquals(0, mockList.size());								// Asserts that two objects are equal. If they are not, an exception is thrown
		
		Mockito.when(mockList.size()).thenReturn(100);							// when: Enables stubbing methods. Use it when you want the mock to return particular value when particular method is called. Simply "When the x method is called then return y". 
		//Mockito.doReturn(100).when(mockList).size();							// Alternative way for stubbing methods
		
		Assert.assertEquals(100, mockList.size());
	}
}
