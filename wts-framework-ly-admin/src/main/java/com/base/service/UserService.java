package com.base.service;

import java.util.List;

import com.base.empty.User;
import com.base.util.context.PageModel;


public interface UserService {
	/**
	 * 通过用户Id查询用户信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public User findByUserId(String userId) throws Exception;
	/**
	 * 通过用户名（手机号）密码登录
	 * @param userName
	 * @param passWord
	 * @return
	 * @throws Exception
	 */
	public User loginUser(String userName, String passWord) throws Exception;
	
	/**
	 * 查找用户
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public PageModel<User> queryUserList(String keys, int pageNo, int pageSize) throws Exception;
	
	/**
	 * 保存用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User save(User user) throws Exception;
	/**
	 * 更新用户
	 * @param user
	 * @return
	 * @throws Excetion
	 */
	public User update(User user) throws Exception;
	
}
