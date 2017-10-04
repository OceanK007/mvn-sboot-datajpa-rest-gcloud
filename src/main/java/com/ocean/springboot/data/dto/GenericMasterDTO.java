package com.ocean.springboot.data.dto;

public class GenericMasterDTO 
{
	private Long id;
	private Long dateCreated;
	private Long dateModified;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Long dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Long getDateModified() {
		return dateModified;
	}
	public void setDateModified(Long dateModified) {
		this.dateModified = dateModified;
	}
}
