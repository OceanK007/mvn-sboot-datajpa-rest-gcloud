package com.ocean.springboot.mockito.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)												// Applicable for Junit 4.4 and higher	// More preferable
public class MockitoSpyAnnotation 
{
	@Spy
	List<String> spyList = new ArrayList<String>();
	
	@Test
	public void withoutSpyAnnotation()
	{
		List<String> spyList = Mockito.spy(new ArrayList<String>());					 
		
		spyList.add("One");														// This will call the real method of list, so size will increase
		//spyList.add("Two");
		
		/** verify: Verifies certain behavior happened once. Arguments passed are compared using equals() method, so it's case-sensitive **/
		Mockito.verify(spyList).add("One");										// True since "One" parameter is passed one time at least
		//Mockito.verify(spyList).add("one");									// false since "one" parameter has not passed (Case-sensitivity)
		//Mockito.verify(spyList, Mockito.times(1)).add("One");					// True since "One" parameter is passed one time at least
		//Mockito.verify(spyList, Mockito.times(2)).add("One");					// Fails since "One" parameter is passed only one time
		//Mockito.verify(spyList, Mockito.times(1)).add(Mockito.anyString());	// True, since any string parameter is passed two times at least
		//Mockito.verify(spyList, Mockito.times(3)).add(Mockito.anyString());	// Fails, since any string parameter is passed two times only
		
		System.out.println("spyList.size(): "+spyList.size()); 					// Will print 1
		Assert.assertEquals(1, spyList.size());									// Asserts that two objects are equal. If they are not, an exception is thrown
		
		Mockito.when(spyList.size()).thenReturn(100);							// when: Enables stubbing methods. Use it when you want the mock to return particular value when particular method is called. Simply "When the x method is called then return y". 
		//Mockito.doReturn(100).when(spyList).size();							// Alternative way for stubbing methods
		
		Assert.assertEquals(100, spyList.size());
	}
	
	@Test
	public void withSpyAnnotation()
	{
		spyList.add("One");														// This will call the real method of list, so size will increase
		//spyList.add("Two");
		
		/** verify: Verifies certain behavior happened once. Arguments passed are compared using equals() method, so it's case-sensitive **/
		Mockito.verify(spyList).add("One");										// True since "One" parameter is passed one time at least
		//Mockito.verify(spyList).add("one");									// false since "one" parameter has not passed (Case-sensitivity)
		//Mockito.verify(spyList, Mockito.times(1)).add("One");					// True since "One" parameter is passed one time at least
		//Mockito.verify(spyList, Mockito.times(2)).add("One");					// Fails since "One" parameter is passed only one time
		//Mockito.verify(spyList, Mockito.times(1)).add(Mockito.anyString());	// True, since any string parameter is passed two times at least
		//Mockito.verify(spyList, Mockito.times(3)).add(Mockito.anyString());	// Fails, since any string parameter is passed two times only
		
		System.out.println("spyList.size(): "+spyList.size()); 					// Will print 1
		Assert.assertEquals(1, spyList.size());									// Asserts that two objects are equal. If they are not, an exception is thrown
		
		Mockito.when(spyList.size()).thenReturn(100);							// when: Enables stubbing methods. Use it when you want the mock to return particular value when particular method is called. Simply "When the x method is called then return y". 
		//Mockito.doReturn(100).when(spyList).size();							// Alternative way for stubbing methods
		
		Assert.assertEquals(100, spyList.size());
	}
}
