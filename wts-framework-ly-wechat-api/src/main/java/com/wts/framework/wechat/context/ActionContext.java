package com.wts.framework.wechat.context;

import com.wts.framework.wechat.util.CMyString;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 
 * Title:  前端数据收集工具类<BR>
 * Description: <BR>
 * TODO 用于前端from、url地址数据收集<BR>
 */
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
    
    public Map<String, Object> getParamsWithStringFilter(PageFormFilter pageFormFilter, String[] fields){
    	pageFormFilter.doFilter(this.params, fields);
    	return params;
    }
    
    public Map<String, Object> getParamsWithFilter() {
    	Set<String> paramsKeys = this.params.keySet();
    	for (String paramKey : paramsKeys) {
    		Object param = this.getParameter(paramKey);
    		if(param instanceof String){
    			this.params.put(paramKey, CMyString.filterForHTMLValue((String)param));
    		}
		}
		return params;
	}
    
    public Map<String, Object> getParamsWithFilter(String[] fields) {
    	if(null == fields)
    		return params;
    	for (String field : fields) {
    		if(null == field)
    			continue;
    		Object param = this.getParameter(field);
    		if(null == param)
    			continue;
    		if(param instanceof String){
    			this.params.put(field.toUpperCase(), CMyString.filterForHTMLValue((String)param));
    		}
		}
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
	
	public String getParameterAsStringWithFilter(String parameterName) {
		return CMyString.filterForHTMLValue((String) this.getParameter(parameterName));
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
	
	public String[] getParameterAsArray(String parameterName) {
		String[] args = this.getParameterAsString(parameterName).split(",");
		return args;
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
        	if("get".equals(CMyString.showNull(method, "").toLowerCase())){
        		ctx.params = populate(request, "UTF-8");
        	}else{
        		ctx.params = populate(request);
        	}
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
}
