package com.ocean.springboot.data.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User extends GenericMasterEntity implements Serializable
{
	private static final long serialVersionUID = 4492258825312384720L;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@JoinColumn(name="role_id")
	@ManyToOne(fetch=FetchType.LAZY)
	private Role role;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
	private List<UserDetail> userDetail;

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

	public List<UserDetail> getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(List<UserDetail> userDetail) {
		this.userDetail = userDetail;
	}
}
