package com.ocean.springboot.data.mongo.entity;

import java.io.Serializable;

import com.ocean.springboot.data.enums.Gender;

public class UserDetail implements Serializable
{
	private static final long serialVersionUID = 1357300399674643886L;

	private String firstName;
	
	private String middleName;
	
	private String lastName;
	
	// It will store it as a string in mongo, and automatically convert when you read it out.
	private Gender gender;
	
	private String email;
	
	private String address;
	
	private String zip;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

}