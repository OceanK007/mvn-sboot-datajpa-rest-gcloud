package com.ocean.springboot.powermockito.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)			
@PrepareForTest({MyDictionaryStatic.class})
public class PowerMockitoInjectMocksAnnotation 
{
	@Test
	public void withInjectMocksAnnotation()
	{
		// In order to mock these static methods, we need to register the enclosing class with the PowerMockito API:
		PowerMockito.mockStatic(MyDictionaryStatic.class);
		
		MyDictionaryStatic.addStatic("testWord", "testMeaning");
		
		PowerMockito.when(MyDictionaryStatic.getMeaningStatic(Mockito.anyString())).thenReturn("testMeaning");
		
		String result = MyDictionaryStatic.getMeaningStatic("testWord");
		
		// The verifyStatic method must be called right before any static method verification for PowerMockito to know that the successive method invocation is what needs to be verified.
		PowerMockito.verifyStatic(Mockito.times(1));
		MyDictionaryStatic.getMeaningStatic(Mockito.anyString());
		
		PowerMockito.verifyStatic(Mockito.times(1));
		MyDictionaryStatic.addStatic(Mockito.anyString(), Mockito.anyString());
		
		Assert.assertEquals(result, "testMeaning");
	}
	
	@Test
	public void throwExceptionOnMethodCallCheck()
	{
		// In order to mock these static methods, we need to register the enclosing class with the PowerMockito API:
		PowerMockito.mockStatic(MyDictionaryStatic.class);
		
		// This will throw RunTimeException when you call test() method of MyDictionaryStatic
		PowerMockito.doThrow(new RuntimeException()).when(MyDictionaryStatic.class);
		MyDictionaryStatic.test();
		
		// Now calling the test() method
		MyDictionaryStatic.test();
	}
}
