package com.base.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.base.empty.User;




public interface UserMapper {
	
   public User findByUserId(@Param("userId") String userId);
   
   public User loginUser(@Param("userName") String userName, @Param("passWord") String passWord);
   
   public List<User> queryListByUser(@Param("keys") String keys, @Param("firstIndex") Integer firstIndex, @Param("pageSize") Integer pageSize);

   public int countUser(@Param("keys") String keys);
   
   public User saveUser(@Param("user") User user);
}
