package com.wts.framework.wechat.web.entity;

import java.util.Map;

public class WxUser  extends  BaseEntity{

    public WxUser(){

    }

    public WxUser(Map<String, Object> props) {
        this.v.putAll(props);
    }
}
