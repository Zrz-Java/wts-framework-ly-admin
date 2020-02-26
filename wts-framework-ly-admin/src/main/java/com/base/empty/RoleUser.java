package com.base.empty;

import java.util.Date;
import java.util.Map;

public class RoleUser {
	private String roleId;
	private String userId;
	private String roleUserId;
	private String crUser;
	private Date crTime;
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoleUserId() {
		return roleUserId;
	}
	public void setRoleUserId(String roleUserId) {
		this.roleUserId = roleUserId;
	}
	public String getCrUser() {
		return crUser;
	}
	public void setCrUser(String crUser) {
		this.crUser = crUser;
	}
	public Date getCrTime() {
		return crTime;
	}
	public void setCrTime(Date crTime) {
		this.crTime = crTime;
	}
	
}
