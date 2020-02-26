<%@page import="com.trt.framework.exception.CMyException"%>
<%@page import="com.trt.framework.ixm.cluster.ClusterDataManager"%>
<%@page import="com.trt.framework.ixm.ids.entity.IDSUser"%>
<%@page import="com.trt.framework.ixm.ids.entity.Enterprise"%>
<%@page import="com.trs.idm.client.actor.SSOUser"%>
<%@page import="com.trt.framework.util.CMyString"%>
<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="com.trt.framework.ixm.ids.IDSAPICall"%>
<%@page import="com.trt.framework.ixm.properties.IdsSettings"%>
<%@page import="com.trt.framework.ixm.context.SpringContextUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/public_include.jsp"%>
<%
	String pwd = request.getParameter("p");
	if(CMyString.isEmpty(pwd) && !"trtadmin".equals(pwd)){
		return;
	}
	
	String userName = request.getParameter("n");

	IdsSettings idsSettings = SpringContextUtil.getBean("idsSettings");
	
	IDSAPICall apiCall = new IDSAPICall(idsSettings.getApiurl(), IdsSettings.ApiType.RemoteApi.getTypeName(), idsSettings.getAppname(), idsSettings.getSecretkey(),
			idsSettings.getDigestalgorithm());
	
	Map<String, String> params = new HashMap<String, String>();
	params.put("userName", userName);
	
	String resultJsonStr = apiCall.Call2Json("userQueryForManage", params);
	JSONObject resultJson = JSONObject.parseObject(resultJsonStr);
	if (resultJson.getIntValue("code") == 0) {
		
		JSONObject userJsonObject = resultJson.getJSONObject("entry");
		Properties userProps = new Properties();
		for (String __key : userJsonObject.keySet()) {
			String __value = userJsonObject.getString(__key);
			if (!CMyString.isEmpty(__value)) {
				userProps.put(__key, __value);
			} else {
				userProps.put(__key, "");
			}
		}
		SSOUser ssoUser = new SSOUser(userProps);
		
		// 非关联用户企业，不可使用
		if ( !"Enterprise".equalsIgnoreCase(ssoUser.getProperty("domainName"))) {
			request.setAttribute("ssoUser", ssoUser);
			request.getRequestDispatcher("/WEB-INF/jsp/u/noauth.jsp").forward(request, response);
			return;
		}
		
		params.clear();
		params.put("groupId", resultJson.getString("groupIdsStr"));
		String enterpriseJsonStr = apiCall.Call2Json("groupQueryByGroupId", params);
	   
		JSONObject enterpriseJsonObject = JSONObject.parseObject(enterpriseJsonStr).getJSONArray("groups").getJSONObject(0);
		Properties enterpriseProps = new Properties();
		
		for (String __key : enterpriseJsonObject.keySet()) {
			String __value = enterpriseJsonObject.getString(__key);
			if (!CMyString.isEmpty(__value)) {
				enterpriseProps.put(__key, __value);
			} else {
				enterpriseProps.put(__key, "");
			}
		}
		
		JSONObject customProperties = enterpriseJsonObject.getJSONObject("customProperties");
		if(null == customProperties){
			enterpriseProps.put("creditCode", "");
		}else{
			enterpriseProps.put("creditCode", customProperties.getString("IDSEXT_UNIFIEDCREDITCODE"));
		}
		
		Enterprise enterprise = new Enterprise(enterpriseProps);
		IDSUser idsUser = new IDSUser(ssoUser, enterprise);
		ClusterDataManager.putIDSUser(request.getSession().getId(), idsUser);
	}
%>