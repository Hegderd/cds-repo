package com.cognizant.cds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cognizant.cds.model.User;
import com.cognizant.cds.repository.UserRepository;

/**
 * User service class
 * 
 * @author Raghavendra Hegde
 */

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> saveAllUsers(final List<User> userList) {
		return userRepository.saveAll(userList);
	}

	@Override
	public List<User> getUsersBySalary(final double salary) {
		return userRepository.findBySalaryLessThanEqualOrderBySalary(salary);
	}
	
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll(Sort.by(Sort.Direction.ASC, "salary"));
	}
}