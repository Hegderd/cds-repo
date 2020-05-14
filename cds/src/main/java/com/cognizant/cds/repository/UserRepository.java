package com.cognizant.cds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.cds.model.User;

/**
 * User repository that uses Spring Data JPA provided methods for database
 * operations
 * 
 * @author Raghavendra Hegde
 */

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	public List<User> findBySalaryLessThanEqualOrderBySalary(double salary);

}
