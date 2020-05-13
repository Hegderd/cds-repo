package com.cognizant.cds.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.cds.exception.InvalidInputException;
import com.cognizant.cds.model.User;
import com.cognizant.cds.service.UserService;
import com.cognizant.cds.validator.Validator;

/**
 * REST API endpoints are exposed to users from this controller
 * 
 * @author Raghavendra Hegde
 */

@CrossOrigin(origins = "*")
@RestController
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	/**
	 * 1. Returns a list of all users (when no salary parameter is provided)
	 * 2. Returns a list of users whose salary is 
	 * between 0 and salary (when salary parameter is provided)
	 * 
	 * @param salary
	 * @return List<User>
	 */
	@GetMapping(value = "/users")
	public ResponseEntity<List<User>> getUsers(
			final @RequestParam(required = false, name = "salary") String salary) {
		
		logger.debug("Started getUsers - " + salary);
		List<User> userList = null;
		
		if (salary == null) {
			userList = userService.getAllUsers();			
		} else {
			Validator validator = new Validator();
			validator.validateSalary(salary);
			if (validator.isError()) {
				throw new InvalidInputException(validator.getErrorMessage());
			}

			userList = userService.getUsersBySalary(Double.parseDouble(salary));
		}

		logger.debug("Completed getUsers");

		return new ResponseEntity<List<User>>(userList, new HttpHeaders(), HttpStatus.OK);
	}
}