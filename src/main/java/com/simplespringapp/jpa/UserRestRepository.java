package com.simplespringapp.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRestRepository extends CrudRepository<User, Long>  {
	 
	List <User> findByRole(String role);
	
}
