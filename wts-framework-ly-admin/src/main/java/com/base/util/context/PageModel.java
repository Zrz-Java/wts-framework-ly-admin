package com.base.util.context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.base.util.CMyString;

/**
 * 页面统一数据收集模型
 * 
 * @author percyLee
 * 
 */
public class PageModel<T> {

	private boolean result;

	private String info;

	/**
	 * 数据结果集
	 */
	private List<T> datas;

	/**
	 * 渲染页面URL
	 */
	private String renderPage = "";

	/**
	 * 数据form提交参数 数据类型为String 或者 String[] 看input的Name多少决定
	 */
	private Map<String, Object> params;
	/**
	 * 分页数据
	 */
	private PageUtil page;
	
	public PageModel() {
		this.page = new PageUtil();
	}

	public PageModel(PageUtil page) {
		this.page = page;
	}

	public PageModel(List<T> datas, PageUtil page) {
		this.datas = datas;
		this.page = page;
	}

	public PageModel(List<T> datas) {
		this.datas = datas;
		this.page = new PageUtil();
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}


	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public String getRenderPage() {
		return renderPage;
	}

	public void setRenderPage(String renderPage) {
		this.renderPage = renderPage;
	}

	/**
	 * 数据form提交参数 数据类型为String 或者 String[] 看input的Name多少决定
	 */
	public Map<String, Object> getParams() {
		return params;
	}

	/**
	 * 数据form提交参数 数据类型为String 或者 String[] 看input的Name多少决定
	 */
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public PageUtil getPage() {
		return page;
	}

	public void setPage(PageUtil page) {
		this.page = page;
	}

	public Object getParameter(String parameterName){
		if(CMyString.isEmpty(parameterName))
			return null;
		return this.getParams().get(parameterName.toUpperCase());
	}

	public String getParameterAsString(String parameterName) {
		return (String) this.getParameter(parameterName);
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
		String[] arg = (String[]) this.getParameter(parameterName);
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < arg.length; i++) {
			list.add(arg[i]);
		}
		return list;
	}

}
