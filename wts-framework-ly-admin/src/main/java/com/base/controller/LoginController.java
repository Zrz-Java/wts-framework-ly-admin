package com.base.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class LoginController extends BaseController{
	
@Autowired
private UserService userService;
	
	@RequestMapping("/m/login.xhtml")
	public ModelAndView loginMobile(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> datas = new HashMap<String, Object>();
		try {
			return new ModelAndView("m/login", datas);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return new ModelAndView("error/error", datas);
		}
	}
	
	@RequestMapping("/m/login_dowith.xhtml")
	public View vlogin(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> datas = new HashMap<String, Object>();
		try {
			String userName = ActionContext.getActionContext().getParameterAsString("userName");
			String passWord = ActionContext.getActionContext().getParameterAsString("passWord");
			String validateCode = ActionContext.getActionContext().getParameterAsString("vCode");
			if(null==request.getSession().getAttribute(AdminValidateCodeController.VALIDATE_CODE_KEY) || "".equals(request.getSession().getAttribute(AdminValidateCodeController.VALIDATE_CODE_KEY))){
	              throw new Exception("获取验证码失败！");
	        }
			if(CMyString.isEmpty(validateCode)){
				throw new Exception("输入的验证码为空！");
			}
			passWord = java.net.URLDecoder.decode(java.net.URLDecoder.decode(passWord));
			userService.loginUser(userName, passWord);
			datas.put("result", true);
			datas.put("msg", "登录成功！");
		} catch (Exception e) {
			e.printStackTrace();
			datas.put("result", false);
			datas.put("msg", e.getMessage());
		}
		
		return new TextView(JSONObject.toJSONStringWithDateFormat(datas, "yyyy-MM-dd HH:mm:ss", features));
	}
		

}
