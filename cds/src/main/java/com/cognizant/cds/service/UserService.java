package com.cognizant.cds.service;

import java.util.List;

import com.cognizant.cds.model.User;

/**
 * @author Raghavendra Hegde
 */

public interface UserService {

	public List<User> saveAllUsers(final List<User> userList);
	
	public List<User> getAllUsers();

	public List<User> getUsersBySalary(final double salary);
}

