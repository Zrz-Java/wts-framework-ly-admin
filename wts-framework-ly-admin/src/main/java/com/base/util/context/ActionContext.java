package com.base.util.context;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.base.util.CMyString;

public final class ActionContext {

    private static final ThreadLocal<ActionContext> actionContextThreadLocal = new ThreadLocal<ActionContext>();

    private HttpServletRequest request;
    private HttpServletResponse response;
    private Map<String, Object> params;

    /**
     * Return current request object.
     */
    public HttpServletRequest getHttpServletRequest() {
        return request;
    }

    /**
     * Return current response object.
     */
    public HttpServletResponse getHttpServletResponse() {
        return response;
    }

    /**
     * Return current session object.
     */
    public HttpSession getHttpSession() {
        return request.getSession();
    }
    
    public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	
    public Object getParameter(String parameterName){
		if(CMyString.isEmpty(parameterName))
			return null;
		if(null == this.getParams())
			return null;
		Object value = this.getParams().get(parameterName.toUpperCase());
		if(null == value)
			return value;
		if(value instanceof String){
			return ((String)value).trim();
		}
		return value;
	}

	public String getParameterAsString(String parameterName) {
		return (String) this.getParameter(parameterName);
	}
	
	public String getParameterAsString(String parameterName, boolean sqlFilter) throws Exception {
		String result = (String) this.getParameter(parameterName);
		if(sqlFilter && sql_inj(result)){
			throw new Exception("[" + parameterName + " = " + result + "]使用sql非法注入！");
		}
		return result;
	}

	public int getParameterAsInt(String parameterName) {
		String arg = (String) this.getParameter(parameterName);
		if (!CMyString.isEmpty(arg)) {
			try{
				return Integer.parseInt(arg);
			}catch (Exception e) {}
		}
		return 0;
	}

	public List<String> getParameterAsList(String parameterName) {
		List<String> list = new ArrayList<String>();
		try{
			String[] arg = (String[]) this.getParameter(parameterName);
			for (int i = 0; i < arg.length; i++) {
				list.add(arg[i]);
			}
		}catch (Exception e) {
			list.add((String)this.getParameter(parameterName));
		}
		return list;
	}
	
	public List<String> getParameterAsList(String parameterName, String split) {
		List<String> list = new ArrayList<String>();
		try{
			String arg = this.getParameterAsString(parameterName);
			String[] argArray = CMyString.showNull(arg, "").split(CMyString.showNull(split, ","));
			
			for (int i = 0; i < argArray.length; i++) {
				list.add(argArray[i]);
			}
		}catch (Exception e) {
			list.add((String)this.getParameter(parameterName));
		}
		return list;
	}
    
	/**
     * Get current ActionContext object.
     */
    public static ActionContext getActionContext() {
        return actionContextThreadLocal.get();
    }
    
    public static void setActionContext(ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        ActionContext ctx = new ActionContext();
        ctx.request = request;
        ctx.response = response;
        try {
        	String method = request.getMethod();
//        	if("get".equals(CMyString.showNull(method, "").toLowerCase())){
//        		ctx.params = populate(request, "UTF-8");
//        	}else{
        		ctx.params = populate(request);
//        	}
		} catch (Exception e) {
		}
        actionContextThreadLocal.set(ctx);
    }

    static void removeActionContext() {
        actionContextThreadLocal.remove();
    }
    
	@SuppressWarnings("unchecked")
	private static Map<String, Object> populate(HttpServletRequest request) throws Exception {
		Map<String, Object> properties = new HashMap<String, Object>();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String[] parameterValue = request.getParameterValues(name);
			for (int i = 0; i < parameterValue.length; i++) {
				parameterValue[i] = parameterValue[i];
			}
			if(parameterValue.length == 1){
				properties.put(name.toUpperCase(), parameterValue[0]);
			}else{
				properties.put(name.toUpperCase(), parameterValue);
			}
		}
		return properties;
	}
	
	@SuppressWarnings("unchecked")
	private static Map<String, Object> populate(HttpServletRequest request, String encoding) throws Exception {
		Map<String, Object> properties = new HashMap<String, Object>();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String[] parameterValue = request.getParameterValues(name);
			for (int i = 0; i < parameterValue.length; i++) {
				parameterValue[i] = new String(parameterValue[i].getBytes("ISO8859-1"), encoding);
			}
			if(parameterValue.length == 1){
				properties.put(name.toUpperCase(), parameterValue[0]);
			}else{
				properties.put(name.toUpperCase(), parameterValue);
			}
		}
		return properties;
	}
	
	public static boolean sql_inj(String str) {
		if(!CMyString.isEmpty(str)){
		String inj_str = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+|,";
		String inj_stra[] = inj_str.split("\\|");
		for (int i = 0; i < inj_stra.length; i++) {
			if (str.indexOf(inj_stra[i]) >= 0) {
				return true;
			}
		}
		return false;
	}
		return false;
	}
}
