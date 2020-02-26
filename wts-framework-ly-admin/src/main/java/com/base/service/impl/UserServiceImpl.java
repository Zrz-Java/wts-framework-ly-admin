package com.base.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.cluster.ClusterDataManager;
import com.base.empty.User;
import com.base.mapping.UserMapper;
import com.base.service.UserService;
import com.base.util.CMyMD5Helper;
import com.base.util.context.ActionContext;
import com.base.util.context.PageModel;
import com.base.util.context.PageUtil;



@Service("userService")
public class UserServiceImpl implements UserService{
@Resource
private UserMapper userMapper;
	
	
	@Override
	public User findByUserId(String userId) throws Exception {
		User user = userMapper.findByUserId(userId);
		return user;
	}


	@Override
	public User loginUser(String userName, String passWord) throws Exception {
		User user = userMapper.loginUser(userName, CMyMD5Helper.string2MD5(passWord).toUpperCase());
		if(null == user){
			throw new Exception("用户名或者密码错误！");
		}
		ClusterDataManager.putUser(ActionContext.getActionContext().getHttpSession().getId(), user);
		return user;
	}


	@Override
	public PageModel<User> queryUserList(String keys,int pageNo,int pageSize) throws Exception {
		PageModel<User> pageModel = new PageModel<User>();
		PageUtil page = new PageUtil(pageNo, pageSize);
		List<User> users = userMapper.queryListByUser(keys,page.getPageOfset(),pageSize);
		int count = userMapper.countUser(keys);
		page.setTotalRecord(count);
		pageModel.setDatas(users);
		pageModel.setPage(page);
		return pageModel;
	}


	@Override
	public User save(User user) throws Exception {
		String passWord = user.getPassWord();
		user.setPassWord(CMyMD5Helper.string2MD5(passWord).toUpperCase());
		user = userMapper.saveUser(user);
		return user;
	}


	@Override
	public User update(User user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
