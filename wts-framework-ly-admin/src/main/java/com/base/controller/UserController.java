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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.empty.User;
import com.base.service.impl.UserServiceImpl;
import com.base.util.CMyString;
import com.base.util.context.ActionContext;
import com.base.util.context.PageModel;
import com.base.util.spring.servlet.TextView;

@Controller
public class UserController extends BaseController{
	
@Autowired
private UserServiceImpl userService;
	
	@RequestMapping("/m/admin/user/list.xhtml")
	public ModelAndView userList(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> datas = new HashMap<String, Object>();
		try {
			return new ModelAndView("/m/admin/user/list", datas);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return new ModelAndView("error/error", datas);
		}
	}
	
	@RequestMapping("/m/admin/user/addedit.xhtml")
	public ModelAndView addedit(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> datas = new HashMap<String, Object>();
		try {
			String userId = ActionContext.getActionContext().getParameterAsString("userId");
			User user = userService.findByUserId(userId);
			datas.put("user", user);
			return new ModelAndView("/m/admin/user/addedit", datas);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return new ModelAndView("error/error", datas);
		}
	}
	
	@RequestMapping("/m/admin/user/list_json.xhtml")
	public View userListJson(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> datas = new HashMap<String, Object>();
		try {
			String keys = ActionContext.getActionContext().getParameterAsString("keys");
			int pageNo = ActionContext.getActionContext().getParameterAsInt("page");
			int pageSize = ActionContext.getActionContext().getParameterAsInt("limit");
			pageNo = pageNo == 0?1:pageNo;
			pageSize = pageSize == 0?10:pageSize;
			PageModel<User> pageModel = userService.queryUserList(keys,pageNo,pageSize);
			
			JSONArray userDatas = new JSONArray();
	
			for(User user : pageModel.getDatas()){
				JSONObject userJson = new JSONObject();
				userJson.put("id", user.getUserId());
				userJson.put("username", user.getUserName());
				userJson.put("sex", user.getSex());
				userJson.put("address", user.getAddress());
				userJson.put("trueName", user.getName());
				userJson.put("mobile", user.getMobile());
				userJson.put("updateTime", user.getUpdateTime());
				userJson.put("status", user.getStatusName());
				userJson.put("crTime", user.getCrTime());
				userDatas.add(userJson);
			}
			datas.put("data", userDatas);
			datas.put("code", 0);
			datas.put("count", pageModel.getPage().getTotalRecord());
			return new TextView(JSONObject.toJSONStringWithDateFormat(datas, "yyyy-MM-dd HH:mm:ss", features));
		} catch (Exception e) {
			e.printStackTrace();
			datas.put("result", false);
			datas.put("msg", e.getMessage());
			return new TextView(JSONObject.toJSONStringWithDateFormat(datas, "yyyy-MM-dd HH:mm:ss", features));
		}
	}
	
	@RequestMapping("/m/admin/user/addedit_dowith.xhtml")
	public View addeditDowith(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> datas = new HashMap<String, Object>();
		try {
			User user = new User();
			user.setPropertiesExt(ActionContext.getActionContext().getParams(), user);
			if (CMyString.isEmpty(user.getUserId())) {
				user = userService.save(user);
			} else {
				user = userService.update(user);
			}
			if (null == user) {
				throw new Exception("数据操作异常！");
			}
			datas.put("result", true);
			datas.put("msg", "数据操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			datas.put("result", false);
			datas.put("msg", e.getMessage());

		}
		return new TextView(JSONObject.toJSONStringWithDateFormat(datas, "yyyy-MM-dd HH:mm:ss", features));
	}	

}
