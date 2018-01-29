package com.ocean.springboot.api.endpoint;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

//@RunWith(SpringRunner.class)	// SpringRunner extends SpringJUnit4ClassRunner which extends BlockJUnit4ClassRunner
@RunWith(MockitoJUnitRunner.class)	// Applicable for Junit 4.4 and higher	// More preferable
@SpringBootTest
@AutoConfigureMockMvc
public class SpringBootApiTest 
{
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getHello() throws Exception
	{
		System.out.println("--------- Test Started ----------");
		mockMvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().string(equalTo("Welcome to Spring Boot")));
		System.out.println("--------- Test Ended ----------");
	}
}
