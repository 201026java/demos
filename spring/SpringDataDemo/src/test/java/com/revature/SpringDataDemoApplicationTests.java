package com.revature;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.lessThan;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.LessThan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

import com.revature.controllers.UserController;
import com.revature.repositories.UserRepository;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

@SpringBootTest
class SpringDataDemoApplicationTests {
	
	@Autowired
	private WebApplicationContext wac;
	
	@Mock
	private UserRepository userDao;
	
	@InjectMocks
	private UserController userController;

	@BeforeEach
	void init() {
		RestAssuredMockMvc.standaloneSetup(userController); // Unit Test setup
//		RestAssuredMockMvc.webAppContextSetup(wac); // Integration Test setup
	}

	@Test
	@Timeout(value = 6L, unit = TimeUnit.SECONDS)
	void testThatGivenNoUsersFindAllReturnsEmptyArrayAndOkStatus() {
		when(userDao.findAll()).thenReturn(Collections.emptyList());
		
		given()
			.when()
				.get("/users")
			.then()
				.log().ifValidationFails()
				.statusCode(200)
				.contentType(ContentType.JSON)
//				.body("[0].username", is("moberlies"))
				.body("$", IsEmptyCollection.empty())
				.time(lessThan(1L), TimeUnit.SECONDS);
	}
}
