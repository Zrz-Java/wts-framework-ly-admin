/**
 * Power By percyLee
 * Copyright: Copyright (c) 2015 percyLee<BR>
 * 
 * @author percyLee (Email:shininglxj@163.com)
 * @version 1.0  2015-6-15
 */
package com.base.util;

import java.security.MessageDigest;

/**
 * Title:  <BR>
 * Description: MD5加密<BR>
 * TODO <BR>
 */
public class CMyMD5Helper {
	
	public static String string2MD5(String inStr){  
        MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");  
        }catch (Exception e){  
            System.out.println(e.toString());  
            e.printStackTrace();  
            return "";  
        }  
        char[] charArray = inStr.toCharArray();  
        byte[] byteArray = new byte[charArray.length];  
  
        for (int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();  
  
    }  

	public static void main(String[] args) {
		System.out.println(CMyMD5Helper.string2MD5("wk123456").toUpperCase());
	}
}
