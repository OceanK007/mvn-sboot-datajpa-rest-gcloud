package com.ocean.springboot.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ocean.springboot.data.entity.User;

/** 
 	Repository Interface 
 			^
 	CrudRepository Interface
 			^
 	PagingAndSortingRepository Interface
 			^
 	JpaRepository Interface
**/

public interface UserRepository extends JpaRepository<User, Long> 
{
	/** 
		If return type is Page and you are using 'FETCH' in query: you have to define countQuery since internally it tries to execute a query to fetch the count.
		And if you don't define countQuery then an exception will occur during run time (on project startup) ::  
	 	Invocation of init method failed; nested exception is java.lang.IllegalArgumentException: Count query validation failed for method public abstract org.springframework.data.domain.Page
	**/
	@Query(value="SELECT u FROM User u LEFT JOIN FETCH u.role r LEFT JOIN FETCH u.userDetail ud", countQuery="SELECT COUNT(u) FROM User u")
	Page<User> findUserByPage(Pageable pageable);
	
	User findById(Long id);
}
