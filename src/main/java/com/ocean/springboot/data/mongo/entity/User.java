package com.ocean.springboot.data.mongo.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/** 
	Indexes are used to find rows with specific column values quickly. Without an index, MySQL must begin with the first row and then read through the entire table to find the relevant rows. 
	The larger the table, the more this costs. If the table has an index for the columns in question, MySQL can quickly determine the position to seek to in the middle of the data file without 
	having to look at all the data. This is much faster than reading every row sequentially.
	
	Updating a table with indexes takes more time than updating a table without (because the indexes also need an update). So, only create indexes on columns that will be frequently searched against.
*/

@Document(collection="user")
public class User implements Serializable
{
	private static final long serialVersionUID = 5908410367071632615L;
	
	@Id
	private String mongoId;		// MongoDB doesn't take Long as id

	private String username;
	
	private String password;
	
	private Role role;
	
	// This is one to one mapping here
	private UserDetail userDetail;
	
	// Use List<Entity> for one to many mapping
	
	public String getUsername() {
		return username;
	}

	public String getMongoId() {
		return mongoId;
	}

	public void setMongoId(String mongoId) {
		this.mongoId = mongoId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}
}