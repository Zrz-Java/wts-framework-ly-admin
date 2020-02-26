package com.wts.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.base.controller.AdminValidateCodeController;
import com.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.alibaba.fastjson.JSONObject;
import com.base.service.UserService;
import com.base.util.CMyString;
import com.base.util.context.ActionContext;
import com.base.util.spring.servlet.TextView;

@Controller
public class CarController extends BaseController{
	
@Autowired
private UserService userService;
	
	@RequestMapping("/m/admin/carqd/index.xhtml")
	public ModelAndView loginMobile(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> datas = new HashMap<String, Object>();
		try {
			return new ModelAndView("m/admin/carqd/index", datas);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return new ModelAndView("error/error", datas);
		}
	}
	
	@RequestMapping("/m/admin/carqd/add.xhtml")
	public View vlogin(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> datas = new HashMap<String, Object>();
		try {

		} catch (Exception e) {
			e.printStackTrace();
			datas.put("result", false);
			datas.put("msg", e.getMessage());
		}
		
		return new TextView(JSONObject.toJSONStringWithDateFormat(datas, "yyyy-MM-dd HH:mm:ss", features));
	}
		

}
