package com.wts.framework.wechat.util;

import java.io.*;
import java.net.*;
import java.util.Iterator;
import java.util.Map;

/*
 * java java HttpURLConnection  工具类
 * ；；2016年9月27日17:48:51
 * zhumc 
 */
public class HttpConnUtil {
    
	//短信机请用此方法获取数据  获取编码与下面的CHARSET 要一致    new String(content.getBytes("ISO-8859-1"),"UTF-8"));
	//public final static String CHARSET = "GB2312";
	public final static String CHARSET = "UTF-8";
	public final static Integer CONNECTTIMEOUT = null;
	public final static Integer SOCKETTIMEOUT = null;
	public final static String PROXYHOST = null;
	public final static Integer PROXYPORT = null;
    
    /**
     * Do GET request
     * @param url
     * @return
     * @throws Exception
     * @throws IOException
     */
    public static String doGet(String url) throws Exception {
        
        URL localURL = new URL(url);
        
        URLConnection connection = openConnection(localURL);
        HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
        
        
        httpURLConnection.setRequestProperty("Accept-Charset", CHARSET);
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("content", "text/html; charset="+CHARSET);
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;
        
        if (httpURLConnection.getResponseCode() >= 300) {
            throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
        }
        
        try {
            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream,CHARSET);
            reader = new BufferedReader(inputStreamReader);
            
            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }
            
        } finally {
            
            if (reader != null) {
                reader.close();
            }
            
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            
            if (inputStream != null) {
                inputStream.close();
            }
            
        }

        return resultBuffer.toString();
    }
    
    /**
     * Do POST request
     * @param url
     * @param parameterMap
     * @return
     * @throws Exception 
     */
    public static String doPost(String url, Map parameterMap) throws Exception {
        
        /* Translate parameter map to parameter date string */
        StringBuffer parameterBuffer = new StringBuffer();
        if (parameterMap != null) {
            Iterator iterator = parameterMap.keySet().iterator();
            String key = null;
            String value = null;
            while (iterator.hasNext()) {
                key = (String)iterator.next();
                if (parameterMap.get(key) != null) {
                    value = (String)parameterMap.get(key);
                } else {
                    value = "";
                }
                
                parameterBuffer.append(key).append("=").append(value);
                if (iterator.hasNext()) {
                    parameterBuffer.append("&");
                }
            }
        }
        
        System.out.println("POST parameter : " + parameterBuffer.toString());
        
        URL localURL = new URL(url);
        
        URLConnection connection = openConnection(localURL);
        HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
        
        // 发送POST请求必须设置如下两行
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Accept-Charset", CHARSET);
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("content", "text/html; charset="+CHARSET);
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(parameterBuffer.length()));
        
        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;
        
        try {
            outputStream = httpURLConnection.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            
            dataOutputStream.write(parameterBuffer.toString().getBytes());
            dataOutputStream.flush();
            
            if (httpURLConnection.getResponseCode() >= 300) {
                throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
            }
            
            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream,CHARSET);
            reader = new BufferedReader(inputStreamReader);
            
            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }
            
        } finally {
            
            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }
            
            if (outputStream != null) {
                outputStream.close();
            }
            
            if (reader != null) {
                reader.close();
            }
            
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            
            if (inputStream != null) {
                inputStream.close();
            }
            
        }

        return resultBuffer.toString();
    }

    private static URLConnection openConnection(URL localURL) throws IOException {
        URLConnection connection;
        if (PROXYHOST != null && PROXYHOST != null) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXYHOST, PROXYPORT));
            connection = localURL.openConnection(proxy);
        } else {
            connection = localURL.openConnection();
        }
        return connection;
    }

    /**
     * Render request according setting
     * @param request
     */
    private void renderRequest(URLConnection connection) {
        
        if (CONNECTTIMEOUT != null) {
            connection.setConnectTimeout(CONNECTTIMEOUT);
        }
        
        if (SOCKETTIMEOUT != null) {
            connection.setReadTimeout(SOCKETTIMEOUT);
        }
        
    }

    public static void main(String[] args) throws Exception {
//        String requestUrl = "http://192.168.160.155:8090/xmsf/xzDetail?appid=nz1h4H1y0MIHVnFp&c_id=09010605";
//        System.out.println("requestUrl==>"+requestUrl);
//        String result = "aMVllhPzVkJ7FL38zs11Z8Ed/PBUhavdAtuLFgD3r689o/Q/5srfWaRgcRnBUGTSx3djWmaPp6CR9v9Ah7T1jHuKFDpi7zfW0+QLk8mfPjvBiOftzOzYOQz3CD04GcnY4Qw7q1Pnat+RDc5NIgakhbEIFg/OXCK3RdM69pECvXPWdzeXJvHBQdzLfKL/8VQFrehSrDuxMN7MMBjyhSBEZKgMYjV1iIYVVQmo0v6/Z71MB4ulAcBki+4HKRjux2Pw1frmNnJ4ODJSz7X0m1Hj1ZrPRuv+lB6Ywl/klB53BKxH29nspLU9OUbCObQOKC0RQFcUlkiiVSBTMxNYIznr4K6cK7Ju/hE7kevR78vdhUGXzjx7H1/qTruZD5bk/PzTX2PsGQzj73/8sa/lfUGL9YLhoDirjdTRdZQrWXp9pQoETBcFcCA7ESMI/Jt0fEj7tFC6eqXUNsT4qWrYrQsIeBBMAmijczTVn/AQGXb/UqWcbp3sF8faaglNMedw1S2AfUBDAEoIIs+Z/+u903gAAPMkF95c/oEHiE1KBUkW5uMY3/AV/d4iY0F2adu/VpePLcTyc+MDav3tCI6LAsE5k7nKIthJlXYPNTbmmTzgjyqG9ZDqiaZ3EibjGPz8+Wh4Dr0SpbeqKT0C94N9P6iVnkCSF+PU2j96IyPFpwDuLGMCnyjDVXehAHZYJ5aZ4hdrnLrBvC+n/wRK2+IkmafQMa9IiYaNWhbmEqBamwmGAPTexEzGSOqpuXcm/3blu2gJpdBwC1gBoaQ6rFYHG8bfqIhWgCPjH5fsI7bgnU46C8gCwI9ZS4Ts2SvpNoMC6eaeG2afY6S06aiSn1/FcvAYQjHhfGW2Drqpd1PNu2jnff1PceFE2scuCGO+5DGvu8NFy7AoQUpucOXl/ZzYbth2F9Coq9erAXIh+OxYssFFJJmN7NjWhL4Rdh0njUoGuiHushP/WFVhRLORwde9wWR4A5rMIy4vXnRZ4FgvyD26ecid6l3uHw1akVaZQAIcVKjz2DDWAytK1TgTMq3VAmVAAdNZYZjAHadT5KyGoOaHQirMuA+cRY52CTdRi0Z+aXtBzJrIm1Ifru19L61/fxIUhirSdrOoMkhH0CHJ+u28KV5ezAlx2vttJMEPTPsBv7RICblepBGWuhgGiHCawbZ1/tgvCdSojCfoHHFlx2YWWhe7n59MZPPAQwL3fRS86KKmz+M2cWpeYHzn28L/rnagn/CRKGI6ujD4bMR54AGiXXs=";
//        JSONObject listJson = JSONObject.parseObject(AesUtil.decrypt(result));
//        System.out.println(listJson);
    }
    
}