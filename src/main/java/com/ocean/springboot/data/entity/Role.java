package com.ocean.springboot.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.ocean.springboot.data.enums.RoleType;

@Entity
@Table(name="role")
public class Role extends AbstractMasterEntity implements Serializable
{
	private static final long serialVersionUID = -967671354022651453L;
	
	@Column(name="role")
	@Enumerated(EnumType.STRING)
	private RoleType roleType;

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}
}
