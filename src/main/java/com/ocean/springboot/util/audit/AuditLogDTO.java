package com.ocean.springboot.util.audit;

import java.io.Serializable;

public class AuditLogDTO implements Serializable 
{
	private static final long serialVersionUID = -3069680202830221730L;

	private String revision_no;
	private String module_name;
	private String entity;
	private String object_id;
	private String namespace;
	private String timestamp;
	private String action;
	private String ip_address;
	private String user_id;
	private String user_email;
	private String reason;
	private String body;
	private String fields_changed;
	private String difference;
	private String parent_id;
	private String person_number;
	
	public String getRevision_no() {
		return revision_no;
	}
	public void setRevision_no(String revision_no) {
		this.revision_no = revision_no;
	}
	public String getModule_name() {
		return module_name;
	}
	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public String getObject_id() {
		return object_id;
	}
	public void setObject_id(String object_id) {
		this.object_id = object_id;
	}
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getFields_changed() {
		return fields_changed;
	}
	public void setFields_changed(String fields_changed) {
		this.fields_changed = fields_changed;
	}
	public String getDifference() {
		return difference;
	}
	public void setDifference(String difference) {
		this.difference = difference;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getPerson_number() {
		return person_number;
	}
	public void setPerson_number(String person_number) {
		this.person_number = person_number;
	}
}
