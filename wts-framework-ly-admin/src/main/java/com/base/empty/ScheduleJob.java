package com.base.empty;

import java.util.Date;

import org.quartz.CronScheduleBuilder;


/**
 * @Description: 计划任务信息
 */
public class ScheduleJob {

	public static final int MODE_ONE_TIME_DAY = 1;
	public static final int MODE_MORE_TIMES_DAY = 2;
	public static final int MODE_ONE_TIME_ONLY = 3;
	public static final int STATUS_NORMAL = 1;
	public static final int STATUS_UNKNOWN = -1;
	public static final int STATUS_NOTSTART = 0;
	public static final int STATUS_WAIT = 1;
	public static final int STATUS_RUN = 5;
	public static final int STATUS_DONE = 7;
	public static final int STATUS_EXPIRED = 8;
	public static final int STATUS_FAILED = 9;
	public static final int STATUS_CANCELED = 10;
	public static final int STATUS_PARAM_INVALID = 11;
	private String schId;
	private String schName;
	private String  schDesc;
	private String  operId;
	private String  schMode;
	private String  execron;
	private String  param;
	private String  attribute;
	private String  crUser;
	private Date  crTime;
	private Date  lastExeTime;
	private int  lastExeres;
	private String  lastTimeUsed;
	private int  schStatus;
	private int  objType;
	private String  objId;
	private String  siteId;
	public String getSchId() {
		return schId;
	}
	public void setSchId(String schId) {
		this.schId = schId;
	}
	public String getSchDesc() {
		return schDesc;
	}
	public void setSchDesc(String schDesc) {
		this.schDesc = schDesc;
	}
	public String getOperId() {
		return operId;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	public String getSchMode() {
		return schMode;
	}
	public void setSchMode(String schMode) {
		this.schMode = schMode;
	}
	public String getExeCron() {
		return execron;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
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
	public int getLastExeres() {
		return lastExeres;
	}
	public void setLastExeres(int lastExeres) {
		this.lastExeres = lastExeres;
	}
	public Date getLastExeTime() {
		return lastExeTime;
	}
	public void setLastExeTime(Date lastExeTime) {
		this.lastExeTime = lastExeTime;
	}
	public String getLastTimeUsed() {
		return lastTimeUsed;
	}
	public void setLastTimeUsed(String lastTimeUsed) {
		this.lastTimeUsed = lastTimeUsed;
	}
	public Integer getSchStatus() {
		return schStatus;
	}
	public void setSchStatus(Integer schStatus) {
		this.schStatus = schStatus;
	}
	public int getObjType() {
		return objType;
	}
	public void setObjType(int objType) {
		this.objType = objType;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
	public String getSchName() {
		return schName;
	}
	public void setSchName(String schName) {
		this.schName = schName;
	}
	

	public boolean setExeCron(String _exeCron) throws Exception {
		if (_exeCron == null)
			throw new Exception("无效的时间表达式(ScheduleJob.setExeCron)");
		try {
			CronScheduleBuilder.cronSchedule(_exeCron);
		} catch (Exception e) {
			throw new Exception( "无效的时间表达式(ScheduleJob.setExeCron)");
		}
		return setExeCron( _exeCron);
	}


}