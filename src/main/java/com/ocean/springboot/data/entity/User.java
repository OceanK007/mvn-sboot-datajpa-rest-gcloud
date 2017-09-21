package com.ocean.springboot.data.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class User extends GenericMasterEntity implements Serializable
{
	private static final long serialVersionUID = 4492258825312384720L;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@JoinColumn(name="profile_id")
	@ManyToOne(fetch=FetchType.LAZY)
	private Profile profile;
	
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

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
}
