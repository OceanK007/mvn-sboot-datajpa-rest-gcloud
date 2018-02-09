package com.ocean.springboot.powermockito.test;

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
import org.mockito.verification.VerificationMode;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)			
@PrepareForTest({MyDictionaryStatic.class})
public class PowerMockitoAndMockitoInjectMocksAnnotation 
{
	// Enabling Mockito Annotations as well. This is same as @RunWith(MockitoJUnitRunner.class)	
	@Before
    public void setUp() throws Exception 
	{
        MockitoAnnotations.initMocks(this);
    }
	 
	@Mock
	private Map<String, String> wordMapMock;
	
	@InjectMocks
	private MyDictionaryStatic myDictionaryStatic = new MyDictionaryStatic();
	
	@Test
	public void withInjectMocksAnnotation()
	{
		// START: Using mockito //
		System.out.println("wordMapMock.size(): "+wordMapMock.size());						// Will print 0, since size() method of wordMap has been mocked.
		
		System.out.println("myDictionary.getSize(): "+myDictionaryStatic.getSize());
		
		Mockito.when(wordMapMock.size()).thenReturn(Integer.valueOf(5));
		Integer sizeResult = myDictionaryStatic.getSize();
		
		Assert.assertEquals(sizeResult, Integer.valueOf(5));		
		
		//Mockito.verify(wordMapMock).size();													// False, since it's called 3 times not one time
		Mockito.verify(wordMapMock, Mockito.times(3)).size();
		// END: Using mockito //
		
		// START: Using powerMockito //
		// In order to mock these static methods, we need to register the enclosing class with the PowerMockito API:
		PowerMockito.mockStatic(MyDictionaryStatic.class);
		
		MyDictionaryStatic.addStatic("testWord", "testMeaning");
				
		PowerMockito.when(MyDictionaryStatic.getMeaningStatic(Mockito.anyString())).thenReturn("testMeaning");
		
		String result = MyDictionaryStatic.getMeaningStatic("testWord");
		
		Assert.assertEquals(result, "testMeaning");
		
		// The verifyStatic method must be called right before any static method verification for PowerMockito to know that the successive method invocation is what needs to be verified.
		PowerMockito.verifyStatic(Mockito.times(1));
		MyDictionaryStatic.getMeaningStatic(Mockito.anyString());
		
		PowerMockito.verifyStatic(Mockito.times(1));
		MyDictionaryStatic.addStatic(Mockito.anyString(), Mockito.anyString());
		// END: Using powerMockito //
	}
}
