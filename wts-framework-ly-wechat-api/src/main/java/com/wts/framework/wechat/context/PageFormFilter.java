package com.wts.framework.wechat.context;

import java.util.Map;

/**
 * Title:  <BR>
 * Description: <BR>
 * TODO <BR>
 * Copyright: Copyright (c) 2013 percyLee<BR>
 * 
 * @author percyLee (Email:shininglxj@163.com)
 * @version 1.0  2014-4-17
 */
public interface PageFormFilter {
	
	/**
	 * @param params 表单属性值
	 * @param fields 过滤字段
	 */
	public void doFilter(Map<String, Object> params, String[] fields);

}
