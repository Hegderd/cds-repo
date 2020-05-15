package com.cognizant.cds.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.cognizant.cds.model.User;
import com.cognizant.cds.service.UserService;

/**
 * Test case for {@link UserController}
 * 
 * @author Raghavendra Hegde
 */

@RunWith(JUnitPlatform.class)
@WebMvcTest
public class UserControllerTest {
	
	private List<User> userList = null;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	UserService userService;
	
	@BeforeEach
	public void setContext() {
		userList = Arrays.asList(
				new User("1234", "Name1", 2000, "name1@test.com"),
				new User("2345", "Name2", 3000, "name2@test.com"), 
				new User("3456", "Name3", 6000, "name2@test.com"));
	}

	/**
	 * Tests /users endpoint with no parameter
	 */
	@Test
	public void testGetUsers() throws Exception {

		Mockito.when(userService.getAllUsers()).thenReturn(userList);

		this.mockMvc.perform(get("/users"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(3)))
				.andExpect(jsonPath("$[1].email", is("name2@test.com")))
				.andExpect(jsonPath("$[1].salary", is(3000.0)));
	}

	/**
	 * Tests for invalid API endpoint
	 */
	@Test
	public void testGetUsers_InvalidResource() throws Exception {

		Mockito.when(userService.getAllUsers()).thenReturn(userList);

		this.mockMvc.perform(get("/alluser"))
				.andExpect(status().isNotFound());
	}

	/**
	 * Tests /users endpoint with valid (numeric value) salary parameter
	 */
	@Test
	public void testGetUsers_WithValidParam() throws Exception {

		Mockito.when(userService.getUsersBySalary(4000)).thenReturn(userList);

		this.mockMvc.perform(get("/users?salary=4000"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(3)))
				.andExpect(jsonPath("$[2].name", is("Name3")))
				.andExpect(jsonPath("$[2].salary", is(6000.0)));
	}
	
	/**
	 * Tests /users endpoint with invalid (non-numeric value) salary parameter 
	 */
	@Test
	public void testGetUsers_WithInvalidParam() throws Exception {

		Mockito.when(userService.getUsersBySalary(4000)).thenReturn(userList);

		this.mockMvc.perform(get("/users?salary=abc"))
				.andExpect(status().isBadRequest());
	}

}
