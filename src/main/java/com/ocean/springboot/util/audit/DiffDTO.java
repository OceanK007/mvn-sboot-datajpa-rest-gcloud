package com.ocean.springboot.util.audit;

import java.util.List;

public class DiffDTO 
{
    private String fieldName;
    private List<String> changes;
    
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public List<String> getChanges() {
		return changes;
	}
	public void setChanges(List<String> changes) {
		this.changes = changes;
	}
}
