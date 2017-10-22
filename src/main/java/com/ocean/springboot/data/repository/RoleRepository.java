package com.ocean.springboot.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocean.springboot.data.entity.Role;
import com.ocean.springboot.data.enums.RoleType;

/** 
	Repository Interface 
			^
	CrudRepository Interface
			^
	PagingAndSortingRepository Interface
			^
	JpaRepository Interface
**/

public interface RoleRepository extends JpaRepository<Role, Long>  
{
	Role findByRoleType(RoleType roleType);
}
