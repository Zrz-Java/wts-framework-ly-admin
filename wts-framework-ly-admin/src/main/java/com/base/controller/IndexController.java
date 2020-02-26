package com.base.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.base.controller.BaseController;
import com.base.service.UserService;

@Controller
public class IndexController extends BaseController{
	
@Autowired
private UserService userService;
	
	@RequestMapping("/m/admin/index/index.xhtml")
	public ModelAndView loginIndex(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> datas = new HashMap<String, Object>();
		try {
			return new ModelAndView("m/admin/index", datas);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return new ModelAndView("error/error", datas);
		}
	}
	@RequestMapping("/m/index/index.xhtml")
	public ModelAndView indexm(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> datas = new HashMap<String, Object>();
		try {
			return new ModelAndView("redirect:/m/admin/index", datas);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return new ModelAndView("error/error", datas);
		}
	}


}
