package com.cognizant.cds.dataloader;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.cognizant.cds.model.User;
import com.cognizant.cds.service.UserService;
import com.cognizant.cds.util.Util;

/**
 * Loads user data from CSV file to database on application startup
 *
 * @author Raghavendra Hegde
 */

@Component
public class UserDataLoader implements ApplicationRunner {

	private final Logger logger = LoggerFactory.getLogger(UserDataLoader.class);
	
	@Autowired
	private UserService userService;

	@Value("${upload-file-path}")
	private String filePath;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info("Started loading data...");

		try {
			List<User> userList = Util.parseCsv(User.class, filePath);
			userService.saveAllUsers(userList);

			logger.info("Completed loading data...");
		} catch (Exception e) {
			logger.error("Error encountered while loading data..." + e.getMessage());
		}
	}
}