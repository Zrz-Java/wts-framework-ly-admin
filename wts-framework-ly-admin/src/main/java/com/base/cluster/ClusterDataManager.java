/**
 * Power By percyLee
 * Copyright: Copyright (c) 2015 percyLee<BR>
 * 
 * @author percyLee (Email:shininglxj@163.com)
 * @version 1.0  2015-6-18
 */
package com.base.cluster;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;

import com.base.empty.User;


/**
 * Title: 集群数据公共存储管理工具<BR>
 * Description: <BR>
 * TODO <BR>
 */
public class ClusterDataManager {

	public static final String VCODE_PREFIX = "VCODE_";
	public static final String SMS_PREFIX = "SMS_";
	public static final String IDSUSER_PREFIX = "User_";
	public static final String OPENID_KEY = "OPENID_";
	public static final String SDTOKENKEY = "SDTOKEN_";

	public static void putVCode(String __key, String __value) {
		put(__key, __value, VCODE_PREFIX);
	}

	public static String getVCode(String __key) {
		return (String) get(__key, VCODE_PREFIX);
	}

	public static void removeVCode(String __key) {
		remove(__key, VCODE_PREFIX);
	}
	
	public static void putOpenId(String __key, String __value) {
		put(__key, __value, OPENID_KEY);
	}
	
	public static String getOpenId(String __key) {
		return (String) get(__key, OPENID_KEY);
	}
	
	public static void removeOpenId(String __key) {
		remove(__key, OPENID_KEY);
	}
	
	public static void putToken(String __key, String __value) {
		put(__key, __value, SDTOKENKEY);
	}
	
	public static String getToken(String __key) {
		return (String) get(__key, SDTOKENKEY);
	}
	
	public static void removeToken(String __key) {
		remove(__key, SDTOKENKEY);
	}

	public static void putSMS(String __key, String __value) {
		Cache cache = createCaChe("SMS_CACHE", 240, 240);
		net.sf.ehcache.Element element = new net.sf.ehcache.Element(__key, __value);
		cache.put(element);
	}

	public static String getSMS(String __key) {
		Cache cache = createCaChe("SMS_CACHE", 240, 240);
		net.sf.ehcache.Element element = cache.get(__key);
		if (null != element) {
			return (String) element.getObjectValue();
		}
		return null;
	}

	public static void removeSMS(String __key) {
		Cache cache = createCaChe("SMS_CACHE", 240, 240);
		cache.remove(__key);
	}

	public static void putUser(String __key, User __value) {
		put(__key, __value, IDSUSER_PREFIX);
	}

	public static User getUser(String __key) {
		return (User) get(__key, IDSUSER_PREFIX);
	}

	public static void removeIDSUser(String __key) {
		remove(__key, IDSUSER_PREFIX);
	}

	public static void put(String __key, Object __value, String __prefix) {
		put(__prefix + __key, __value);
	}

	public static void remove(String __key, String __prefix) {
		remove(__prefix + __key);
	}

	public static Object get(String __key, String __prefix) {
		return get(__prefix + __key);
	}

	public static void put(String __key, Object __value) {
		Cache chCache = createCaChe("TEMP_TT_CACHE", 3600, 3600);
		net.sf.ehcache.Element element = new net.sf.ehcache.Element(__key, __value);
		chCache.put(element);
	}

	public static Object get(String __key) {
		Cache chCache = createCaChe("TEMP_TT_CACHE", 3600, 3600);
		net.sf.ehcache.Element element = chCache.get(__key);
		if (null != element) {
			return element.getObjectValue();
		}
		return null;
	}

	public static void remove(String __key) {
		Cache chCache = createCaChe("TEMP_TT_CACHE", 3600, 3600);
		chCache.remove(__key);
	}

	private static Cache createCaChe(String cacheName, long setTimeToLiveSeconds, long setTimeToIdleSeconds) {
		CacheManager manager = CacheManager.create();
		Cache chCache = manager.getCache(cacheName);
		if (null == chCache) {
			manager.addCache(cacheName);
			chCache = manager.getCache(cacheName);
			CacheConfiguration config = chCache.getCacheConfiguration();
			config.setTimeToLiveSeconds(setTimeToLiveSeconds);
			config.setTimeToIdleSeconds(setTimeToIdleSeconds);
		}
		return chCache;
	}

}
