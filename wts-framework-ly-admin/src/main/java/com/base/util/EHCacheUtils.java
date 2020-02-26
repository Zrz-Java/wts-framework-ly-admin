/**
 * Power By percyLee
 * Copyright: Copyright (c) 2017 percyLee<BR>
 * 
 * @author percyLee (Email:shininglxj@163.com)
 * @version 1.0  2017-3-6
 */
package com.base.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;

/**
 * Title:  <BR>
 * Description: <BR>
 * TODO <BR>
 */
public class EHCacheUtils {
	
	private static CacheManager manager = CacheManager.create();
	
	public static Object getCacheValue(String cacheSql) {
		
		Cache cache = manager.getCache("SQL_CACHE");
		net.sf.ehcache.Element element = cache.get(cacheSql);
		if (null != element) {
			return element.getObjectValue();
		}
		return null;
	}

	public static void setCacheValue(String cacheSql, Object result) {
		Cache cache = manager.getCache("SQL_CACHE");
		net.sf.ehcache.Element element = new net.sf.ehcache.Element(cacheSql, result);
		cache.put(element);
	}
	
	public static void putTempObj(String __key, Object __value, Integer time){
		CacheManager manager = CacheManager.create();
		Cache chCache = manager.getCache("TEMP_TT_CACHE");
		if(null == chCache){
			manager.addCache("TEMP_TT_CACHE"); 
			chCache = manager.getCache("TEMP_TT_CACHE");
			
		}
		CacheConfiguration config = chCache.getCacheConfiguration();
		config.setTimeToLiveSeconds(time);
		config.setTimeToIdleSeconds(time);
		net.sf.ehcache.Element element = new net.sf.ehcache.Element(__key, __value);
		chCache.put(element);
	}
	
	public static Object getTempObj(String __key){
		CacheManager manager = CacheManager.create();
		Cache chCache = manager.getCache("TEMP_TT_CACHE");
		if(null == chCache){
			manager.addCache("TEMP_TT_CACHE");
			chCache = manager.getCache("TEMP_TT_CACHE");
		}
		net.sf.ehcache.Element element = chCache.get(__key);
		if (null != element) {
			return element.getObjectValue();
		}
		return null;
	}
	
	public static Object getTempObjExpirationTime(String __key){
		CacheManager manager = CacheManager.create();
		Cache chCache = manager.getCache("TEMP_TT_CACHE");
		if(null == chCache){
			manager.addCache("TEMP_TT_CACHE");
			chCache = manager.getCache("TEMP_TT_CACHE");
		}
		net.sf.ehcache.Element element = chCache.get(__key);
		if (null != element) {
			return element.getExpirationTime();
		}
		return null;
	}

}
