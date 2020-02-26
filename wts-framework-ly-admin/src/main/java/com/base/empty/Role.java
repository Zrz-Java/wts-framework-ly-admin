package com.base.empty;

import java.util.Date;

public class Role {
	public static final int OBJ_TYPE = 5401;
	
	private String roleId;
	private String roleName;
	private String roleDesc;
	private String attribute;
	private int sysDefined;
	private int viewAble;
	private int removeAble;
	private String crUser;
	private Date crTime;
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public int getSysDefined() {
		return sysDefined;
	}
	public void setSysDefined(int sysDefined) {
		this.sysDefined = sysDefined;
	}
	public int getViewAble() {
		return viewAble;
	}
	public void setViewAble(int viewAble) {
		this.viewAble = viewAble;
	}
	public int getRemoveAble() {
		return removeAble;
	}
	public void setRemoveAble(int removeAble) {
		this.removeAble = removeAble;
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
