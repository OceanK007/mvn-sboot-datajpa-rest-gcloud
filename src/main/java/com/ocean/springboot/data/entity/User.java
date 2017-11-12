package com.ocean.springboot.data.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/** 
	Indexes are used to find rows with specific column values quickly. Without an index, MySQL must begin with the first row and then read through the entire table to find the relevant rows. 
	The larger the table, the more this costs. If the table has an index for the columns in question, MySQL can quickly determine the position to seek to in the middle of the data file without 
	having to look at all the data. This is much faster than reading every row sequentially.
	
	Updating a table with indexes takes more time than updating a table without (because the indexes also need an update). So, only create indexes on columns that will be frequently searched against.
*/

@Entity
@Table(name="user",
indexes = {@Index(name="username_index", columnList="username", unique=true), 
		   @Index(name="role_index", columnList="role_id", unique=false)})
public class User extends AbstractMasterEntityWithZone implements Serializable
{
	private static final long serialVersionUID = 4492258825312384720L;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@JoinColumn(name="role_id", nullable=false)
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Role role;
	
	@JoinColumn(name="user_detail_id", nullable=false, unique=true)
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL, optional=false)
	private UserDetail userDetail;

	public String getUsername() {
		return username;
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
