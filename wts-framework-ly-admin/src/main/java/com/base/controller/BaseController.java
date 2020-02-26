package com.base.controller;

import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.base.cluster.ClusterDataManager;
import com.base.empty.User;
import com.base.util.context.ActionContext;

public abstract class BaseController {
	    public static final SerializerFeature[] features = { SerializerFeature.WriteMapNullValue, // 输出空置字段
			SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
			SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
			SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
			SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
	    };
	   
		public User getLoginUser(){
			HttpSession session = ActionContext.getActionContext().getHttpSession();
			return ClusterDataManager.getUser(session.getId());
		}

}
