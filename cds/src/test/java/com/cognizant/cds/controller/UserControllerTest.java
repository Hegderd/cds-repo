package com.cognizant.cds.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cognizant.cds.exception.InvalidInputException;
import com.cognizant.cds.model.User;
import com.cognizant.cds.service.UserService;

/**
 * Test case for {@link UserController}
 * 
 * @author Raghavendra Hegde
 */

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class UserControllerTest {

	@InjectMocks
	private UserController userController;

	@Mock
	private UserService userService;

	@BeforeClass
	public void setContext() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	}

	/**
	 * Tests getUsers() with no parameter
	 */
	@Test
	public void testGetUsers() throws Exception {
		List<User> userList = Arrays.asList(
				new User("1234", "Name1", 2000, "name1@test.com"),
				new User("2345", "Name2", 3000, "name2@test.com"),
				new User("3456", "Name3", 6000, "name2@test.com"));

		Mockito.when(userService.getAllUsers()).thenReturn(userList);

		ResponseEntity<List<User>> responseEntity = userController.getUsers(null);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody().size()).isEqualTo(3);
	}

	/**
	 * Tests getUsers() method with valid parameter (numeric)
	 */
	@Test
	public void testGetUsers_WithValidParam() throws Exception {
		List<User> userList = Arrays.asList(
				new User("1234", "Name1", 2000, "name1@test.com"),
				new User("2345", "Name2", 3000, "name2@test.com"));

		Mockito.when(userService.getUsersBySalary(4000)).thenReturn(userList);

		ResponseEntity<List<User>> responseEntity = userController.getUsers("4000");

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody().size()).isEqualTo(2);
	}

	/**
	 * Tests getUsers() method with invalid parameter (non-numeric)
	 */
	@Test
	public void testGetUsers_WithInvalidParam() throws Exception {
		Assertions.assertThrows(InvalidInputException.class, () -> userController.getUsers("abc"));
	}

}
