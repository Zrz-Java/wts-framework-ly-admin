package com.wts.framework.wechat.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.wts.framework.wechat.context.ActionContext;
import com.wts.framework.wechat.spring.servlet.TextView;
import com.wts.framework.wechat.util.CMyString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UmessageSendController {
	

	/**
	 * 重新发送短信
	 * @param request
	 * @param response
	 * @return
	 */
	@PostMapping("/u/sendMsg.xhtml")
	public View sendMt(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> datas = new HashMap<String,Object>();
		try{
		    String mobile = ActionContext.getActionContext().getParameterAsString("mobile");
            if(CMyString.isEmpty(mobile)){
                throw new Exception("手机号码不能为空");
            }
			request.getSession().setAttribute("mobile_code", "1234");
			datas.put("result", true);
			datas.put("msg", "发送成功");
		} catch (Exception e) {
			datas.put("result", false);
			datas.put("msg", e.getMessage());
		}
		return new TextView(JSONObject.toJSONString(datas));
	}
	

}
