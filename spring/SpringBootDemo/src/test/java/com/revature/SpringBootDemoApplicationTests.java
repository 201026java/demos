package com.revature;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.revature.controllers.HelloController;

@SpringBootTest
class SpringBootDemoApplicationTests {
	
//	@Autowired
//	private WebApplicationContext wac;

	private MockMvc mock;
	
	@BeforeEach
	public void initBeforeTest() {
		mock = MockMvcBuilders.standaloneSetup(new HelloController()).build(); // Used for Unit Tests
//		mock = MockMvcBuilders.webAppContextSetup(wac).build(); // Used for Integration Tests
	}
	
	@Test
	void testHelloWorld() throws Exception {
		mock.perform(get("/hello"))
			.andDo(print())
			.andExpect(status().isIAmATeapot())
			.andExpect(jsonPath("$").value("Hello World! This is my very first Spring Boot Application!"));
	}
	
	@Test
	void testPostNotAllowed() throws Exception {
		mock.perform(post("/hello"))
		.andDo(print())
		.andExpect(status().isMethodNotAllowed());
	}
}
