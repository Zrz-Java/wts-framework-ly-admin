package com.base.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.base.util.CMyString;

public class MyTruncateTag extends TagSupport {

	private static final long serialVersionUID = -7838375046724489771L;
	protected String value;
	protected Integer length;

	private String subString(String tempstr, int length) {
		return CMyString.truncateStr(tempstr, length, "...");
	}

	// for tag attribute
	public void setValue(String _sValue) {
		this.value = _sValue;
	}

	// for tag attribute
	public void setLength(Integer length) {
		this.length = length;
	}

	public int doStartTag() throws JspException {
		JspWriter w = pageContext.getOut();
		try {
			w.print(subString(CMyString.Html2Text(CMyString.showNull(value, "")), length));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
}
