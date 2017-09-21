package com.ocean.springboot.data.entity;

import java.io.Serializable;

import com.ocean.springboot.data.enums.ProfileType;

public class Profile extends GenericMasterEntity implements Serializable
{
	private static final long serialVersionUID = -967671354022651453L;
	
	private ProfileType profileType;

	public ProfileType getProfileType() {
		return profileType;
	}

	public void setProfileType(ProfileType profileType) {
		this.profileType = profileType;
	}
}
