package com.base.tag;

import javax.annotation.Resource;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.base.empty.Action;
import com.base.mapping.ActionMapper;
import com.base.util.CMyString;

/**
 * Title: <BR>
 * Description: <BR>
 * TODO <BR>
 */
public class CMyActionTag extends TagSupport {
	@Resource
	private ActionMapper actionMapper;
	
	private static final long serialVersionUID = -8627305302729836771L;
	private Integer actionId = 0;
	private String field = "ACNAME";
	private Integer num = 10000;

	@Override
	public int doStartTag() throws JspException {
		
		JspWriter w = pageContext.getOut();
		field = CMyString.isEmpty(field) ? "CNAME" : field;
		try {
			Action action = actionMapper.findByAcId(actionId);
			if(null != action){
				//String fieldValue = action.getPropertyAsString(field, "");
				if(num > 0){
					//fieldValue = CMyString.truncateStr(fieldValue, num, "...");
				}
				//w.print(fieldValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	@Override
	public void release() {
		super.release();
	}


	/**
	 * @return the actionId
	 */
	public Integer getActionId() {
		return actionId;
	}

	/**
	 * @param actionId the actionId to set
	 */
	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * @return the num
	 */
	public Integer getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(Integer num) {
		this.num = num;
	}


	

	
}
