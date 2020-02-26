package com.base.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


/**
 * 
 * Title:  微信https数据调用通用处理�?BR>
 * Description: <BR>
 * TODO <BR>
 * Copyright: Copyright (c) 2013 percyLee<BR>
 * 
 * @author percyLee (Email:shininglxj@163.com)
 * @version 1.0
 */
public class CMyHttpClientUtil {
	
	public interface IHttpConnectSet{
		/*
		 * 将连接头的所有参数�?过这个接口来传�?进去进行请求
		 */
		public void iHttpConnectSet(HttpURLConnection httpUrlConn);
	}
	/**
	 * 发�?https请求
	 * 
	 * @param requestUrl
	 * @param requestMethod
	 * @param outputStr
	 * @return
	 */
	public static String httpRequest(String requestUrl, String requestMethod, String outputStr,IHttpConnectSet it) throws Exception {
		String content = "";
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			httpUrlConn.setConnectTimeout(20000);  
			httpUrlConn.setReadTimeout(300000);
			it.iHttpConnectSet(httpUrlConn);
			// 设置请求方式（GET/POST�?
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
			// 当有数据�?��提交�?
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱�?
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			int contentLength = 0;
			// 将返回的输入流转换成字符�?
			//InputStream inputStream = httpUrlConn.getInputStream();
			InputStream inputStream;
			if (httpUrlConn.getResponseCode() >= 400) {
				inputStream = httpUrlConn.getErrorStream();
			} else {
				inputStream = httpUrlConn.getInputStream();
			}
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				int flag = str.indexOf("Content-Length");
				if (flag != -1) {
					contentLength = Integer.parseInt((str.split(":")[1]).trim());
				}
				buffer.append(str);
			}
			if (contentLength > 0) {
				content = buffer.substring(buffer.length() - contentLength);
			} else {
				content = buffer.toString();
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("[httpRequest{" + requestMethod + "}]出错");
		}
		return content;
	}

	public static String urlConnect(String url) throws Exception{
		String content = "";
		StringBuffer buffer = new StringBuffer();
		URL realUrl = new URL(url); 
		URLConnection connection = realUrl.openConnection();
	
		connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
		connection.setRequestProperty("Charset", "UTF-8");
		connection.setRequestProperty("COLLAGEN-REQUESTER_ID", "XM.GOV.APP.HC.QXJZZ");
		connection.setRequestProperty("COLLAGEN-AUTHORIZE_ID", "735e8f62a8094028883d5482942effb1");
		connection.setRequestProperty("COLLAGEN-PROXY_FLOW_ID", "XM.HC::01::FLOW_C3_CALL_RESTFUL_PROXY");
		//String sessionId = UUIDGenerator.generate();	
		connection.setRequestProperty("COLLAGEN-SESSION_ID", "no-session");
		connection.setRequestProperty("COLLAGEN-TIMEOUT", "30S");
		connection.setRequestProperty("COLLAGEN-DEBUG", "OFF");
		connection.setRequestProperty("COLLAGEN-OUT_PARAMETERS", "_ALL_BUT_EXCEPTION_");
		connection.connect();
		
		int contentLength = 0;
		// 将返回的输入流转换成字符�?
		//InputStream inputStream = httpUrlConn.getInputStream();
		InputStream inputStream;
		inputStream = connection.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String str = null;
		while ((str = bufferedReader.readLine()) != null) {
			int flag = str.indexOf("Content-Length");
			if (flag != -1) {
				contentLength = Integer.parseInt((str.split(":")[1]).trim());
			}
			buffer.append(str);
		}
		if (contentLength > 0) {
			content = buffer.substring(buffer.length() - contentLength);
		} else {
			content = buffer.toString();
		}
		bufferedReader.close();
		inputStreamReader.close();
		// 释放资源
		inputStream.close();
		inputStream = null;
		return content;
	}
	
	public static String Call(String requestUrl, String requestMethod, String outputStr) throws Exception {
		String content = "";
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			httpUrlConn.setConnectTimeout(20000);
			httpUrlConn.setReadTimeout(300000);
			// 设置请求方式（GET/POST�?
			httpUrlConn.setRequestMethod(requestMethod);
			
			//httpUrlConn.setRequestProperty("Host", " www.ixm.gov.cn");
			httpUrlConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
			httpUrlConn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			httpUrlConn.setRequestProperty("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
			httpUrlConn.setRequestProperty("Accept-Encoding", "gzip, deflate");
			httpUrlConn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			httpUrlConn.setRequestProperty("Content-Length", String.valueOf(0));
			httpUrlConn.setRequestProperty("Transfer-encoding", "chunked");
			//httpUrlConn.settransfer-encoding：chunked
			
			/*httpUrlConn.setRequestProperty("COLLAGEN-REQUESTER_ID", "HMPT.ICX");
			httpUrlConn.setRequestProperty("COLLAGEN-AUTHORIZE_ID", "72da0fd3540c73973832401b4206f5de");
			httpUrlConn.setRequestProperty("COLLAGEN-PROXY_FLOW_ID", "XM.CISP::01::FLOW_COMMON_CISP_HMPT");
			httpUrlConn.setRequestProperty("COLLAGEN-SESSION_ID", "74702dc1-248a-4d0a-9d6e-a2dd214eef68");
			httpUrlConn.setRequestProperty("COLLAGEN-TIMEOUT", "30S");
			httpUrlConn.setRequestProperty("COLLAGEN-DEBUG", "OFF");
			httpUrlConn.setRequestProperty("COLLAGEN-OUT_PARAMETERS", "_ALL_");*/
			httpUrlConn.setRequestProperty("COLLAGEN-REQUESTER_ID", "XM.GOV.APP.HC.QXJZZ");
			httpUrlConn.setRequestProperty("COLLAGEN-AUTHORIZE_ID", "735e8f62a8094028883d5482942effb1");
			httpUrlConn.setRequestProperty("COLLAGEN-PROXY_FLOW_ID", "XM.HC::01::FLOW_C3_CALL_RESTFUL_PROXY");
			//String sessionId = UUIDGenerator.generate();	
			httpUrlConn.setRequestProperty("COLLAGEN-SESSION_ID", "no-session");
			httpUrlConn.setRequestProperty("COLLAGEN-TIMEOUT", "30S");
			httpUrlConn.setRequestProperty("COLLAGEN-DEBUG", "OFF");
			httpUrlConn.setRequestProperty("COLLAGEN-OUT_PARAMETERS", "_ALL_BUT_EXCEPTION_");

			//connection.setRequestProperty("Cookie", "Hm_lvt_5da6686d40e4dcf682a48ca464e4c138=1417404351,1417671109,1418042206; trsidssdssotoken=E5968060E8C77A6FDD5DC8932A8E55FD-10.2.21.14_1420516017822");
			
			// 当有数据�?��提交�?
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱�?
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			int contentLength = 0;
			// 将返回的输入流转换成字符�?
			// InputStream inputStream = httpUrlConn.getInputStream();
			InputStream inputStream;
			if (httpUrlConn.getResponseCode() >= 400) {
				inputStream = httpUrlConn.getErrorStream();
			} else {
				inputStream = httpUrlConn.getInputStream();
			}
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				int flag = str.indexOf("Content-Length");
				if (flag != -1) {
					contentLength = Integer.parseInt((str.split(":")[1]).trim());
				}
				buffer.append(str);
			}
			if (contentLength > 0) {
				content = buffer.substring(buffer.length() - contentLength);
			} else {
				content = buffer.toString();
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("[httpRequest{" + requestMethod + "}]出错");
		}
		
		return content;
	}
}
