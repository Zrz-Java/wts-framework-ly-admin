package com.wts.framework.wechat.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wts.framework.wechat.context.ActionContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @Title: ActionContextInterceptor.java
 * @Package com.trt.framework.wechat.web.interceptor
 * @Description: 全局POST、GET参数处理拦截器
 * @author percyLee
 * @date 2017年12月30日 上午12:51:27
 */
public class ActionContextInterceptor implements HandlerInterceptor {
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception e) throws Exception {
		
	}
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
	}
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		ActionContext.setActionContext(request.getSession().getServletContext(), request, response);
		return true;
	}
	
}
