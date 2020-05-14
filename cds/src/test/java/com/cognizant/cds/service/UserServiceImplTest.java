package com.cognizant.cds.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cognizant.cds.model.User;
import com.cognizant.cds.repository.UserRepository;

/**
 * Test case for {@link UserServiceImpl}
 * 
 * @author Raghavendra Hegde
 */

@SpringBootTest
public class UserServiceImplTest {
	
	@MockBean
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	/**
	 * Tests saveAllUsers() method
	 */
	@Test
	public void testSaveAllUsers() {
		List<User> userList = Arrays.asList(
				new User("1234", "Name1", 2000, "name1@test.com"),
				new User("2345", "Name2", 3000, "name2@test.com"));

		List<User> returnedUserList = Arrays.asList(
				new User("1234", "Name1", 2000, "name1@test.com"),
				new User("2345", "Name2", 3000, "name2@test.com"));

		Mockito.when(userRepository.saveAll(userList)).thenReturn(returnedUserList);

		List<User> savedUserList = userService.saveAllUsers(userList);

		assertThat(savedUserList.size()).isEqualTo(2);
	}

	/**
	 * Tests getUsersBySalary() method
	 */
	@Test
	public void testGetUsersBySalary() {
		List<User> userList = Arrays.asList(
				new User("2222", "Name2", 2000, "name2@test.com"),
				new User("4444", "Name3", 3000, "name3@test.com"),
				new User("3333", "Name4", 4000, "name4@test.com"));

		Mockito.when(userRepository.findBySalaryLessThanEqualOrderBySalary(4100)).thenReturn(userList);

		List<User> returnedUserList = userService.getUsersBySalary(4100);

		assertThat(returnedUserList.size()).isEqualTo(3);
		assertThat(returnedUserList.get(2).getName()).isEqualTo("Name4");
	}

}
