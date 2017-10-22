package com.ocean.springboot.data.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user")
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
