package com.wts.framework.wechat;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Title: DefaultView.java
 * @Package com.trt.framework.ixm
 * @Description: TODO
 * @author percyLee
 * @date 2017年12月30日 上午12:24:15
 */
@Configuration
public class DefaultView extends WebMvcConfigurerAdapter {
	
	/**
	 * 默认欢迎首页
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/index.html");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		super.addViewControllers(registry);
	}
}