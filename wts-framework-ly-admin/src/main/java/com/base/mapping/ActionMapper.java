package com.base.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.base.empty.Action;




public interface ActionMapper {
	
   public Action findByAcId(@Param("acId") Integer acId);
   
   public List<Action> queryByParentId(@Param("parentId") Integer parentId, @Param("firstIndex") Integer firstIndex, @Param("pageSize") Integer pageSize);
   
   public int countByParentId(@Param("parentId") Integer parentId);
}
