package com.base.empty;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.base.persistent.BaseUser;



public class User extends BaseUser{
	

	private static final long serialVersionUID = 1L;
	public static final int OBJ_TYPE = 101;
	public static final String DB_TABLE_NAME = "WE_USER";
	public static final String DB_ID_NAME = "USERID";
	public static final boolean NEED_CACHE = false;
	
	
	@Override
	public int getEntityNo() {
		return OBJ_TYPE;
	}

	@Override
	public String getTableName() {
		return DB_TABLE_NAME;
	}

	@Override
	public String getPriKeyName() {
		return DB_ID_NAME;
	}

	@Override
	public boolean needCache() {
		return NEED_CACHE;
	}
	
	@Override
	public String getTrueName() {
		return null;
	}
	
	private String userId;
	
	private String userName;
	
	private String passWord;
	
	private String name;
	
	private int age;
	
	private String sex;
	
	private String mobile;
	
	private String email;
	
	private String address;
	
	private String sign;
	
	private String headUrl;
	
	private int status;
	
	private String idCard;
	
	private Date crTime;
	
	private Date updateTime;
	
	private Date logoutTime;
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public Date getCrTime() {
		return crTime;
	}

	public void setCrTime(Date crTime) {
		this.crTime = crTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	} 

	
	
	public boolean isAdministrator() {
		List<Role> roles = this.getRoles();
		for (Role role : roles) {
			if (null == role)
				continue;
			if ("administrator".equals(role.getRoleName().toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	
	public List<Role> getRoles(){
		return null;
		
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getStatusName(){
		if(this.getStatus()==0){
			return "停用";
		}else if(this.getStatus()==1){
			return "正常";
		}else if(this.getStatus()==-1){
			return "锁定";
		}
		return "";
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}


	
}
