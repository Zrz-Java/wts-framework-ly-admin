package com.base.Filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.base.cluster.ClusterDataManager;
import com.base.empty.User;
import com.base.util.CMyString;

public class MFilter implements Filter {
	
	private String __Url;

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		User user = ClusterDataManager.getUser(httpServletRequest.getSession().getId());
		String _sUrl = null;
		if (null == user) {
			if(CMyString.isEmpty(__Url)){
				_sUrl = httpServletRequest.getContextPath();
			}else{
				_sUrl = httpServletRequest.getContextPath() + __Url;
			}
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			StringBuffer sbBuffer = new StringBuffer();
			sbBuffer.append("<script type='text/javascript'>");
			sbBuffer.append("	var contentWin = window.parent || window;");
			sbBuffer.append("	contentWin.location.href='" + _sUrl + "';");
			sbBuffer.append("</script>");
			out.write(sbBuffer.toString());
			return;
		}
		filterChain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		__Url= filterConfig.getInitParameter("url");
	}

}
