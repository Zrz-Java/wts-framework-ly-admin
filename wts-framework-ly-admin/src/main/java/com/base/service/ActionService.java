package com.base.service;

import java.util.List;

import com.base.empty.Action;


public interface ActionService {
	public Action findByAcId(int acId) throws Exception;
	
	public List<Action> findByParentId(int parentId, int pageNo, int pageSize) throws Exception;
	
	public Integer countByParentId(int parentId) throws Exception;
	
	
	
}
