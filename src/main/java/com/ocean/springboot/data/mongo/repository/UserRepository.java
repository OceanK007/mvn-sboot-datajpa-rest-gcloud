package com.ocean.springboot.data.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.ocean.springboot.data.mongo.entity.User;

/** 
 	Repository Interface 
 			^
 	CrudRepository Interface
 			^
 	PagingAndSortingRepository Interface
 			^
 	MongoRepository Interface
**/

//If you don't user @Repository(value="userMongoRepository") then spring will try to create bean with name userRepository which already exist since we have another UserRepository file and it will throw exception.
@Repository(value="userMongoRepository")
public interface UserRepository extends MongoRepository<User, String>
{
	User findByUsername(String username);
	
	@Query(value="{'userDetail.address': {$regex: ?0}}")
	List<User> findByAddress(String address);
}