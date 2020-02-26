package com.base.empty;

import java.util.Date;

public class ScheduleJobLog {

	private String scheduleJobLogId;
	private String chnlId;
	private String siteId;
	private String param;
	private String apiresult;
	private String apimsg;
	private String remark;
	private Date crTime;
	
	public String getScheduleJobLogId() {
		return scheduleJobLogId;
	}
	public void setScheduleJobLogId(String scheduleJobLogId) {
		this.scheduleJobLogId = scheduleJobLogId;
	}
	public String getChnlId() {
		return chnlId;
	}
	public void setChnlId(String chnlId) {
		this.chnlId = chnlId;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getApiresult() {
		return apiresult;
	}
	public void setApiresult(String apiresult) {
		this.apiresult = apiresult;
	}
	public String getApimsg() {
		return apimsg;
	}
	public void setApimsg(String apimsg) {
		this.apimsg = apimsg;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCrTime() {
		return crTime;
	}
	public void setCrTime(Date crTime) {
		this.crTime = crTime;
	}

}
