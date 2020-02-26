package com.base.persistent;

public abstract class BaseUser extends Entity{

	
	private static final long serialVersionUID = -3576451932413246162L;

	public abstract String getUserId();

	public abstract String getUserName();

	public abstract String getTrueName();
	
	public abstract String getMobile();
}
