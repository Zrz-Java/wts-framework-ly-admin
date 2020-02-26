package com.base.util.spring.servlet;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractUrlBasedView;

public class TextView extends AbstractUrlBasedView {

	private String outTextValue = "";

	public TextView(String textValue) {
		outTextValue = textValue;
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().write(outTextValue);
	}

}
