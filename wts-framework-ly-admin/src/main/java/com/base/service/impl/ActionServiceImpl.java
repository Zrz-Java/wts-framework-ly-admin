package com.base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.empty.Action;
import com.base.mapping.ActionMapper;
import com.base.service.ActionService;
import com.base.util.context.PageUtil;



@Service("actionService")
public class ActionServiceImpl implements ActionService{
@Resource
private ActionMapper actionMapper;

@Override
public Action findByAcId(int acId) throws Exception {
	Action action = actionMapper.findByAcId(acId);
	return action;
}

@Override
public List<Action> findByParentId(int parentId, int pageNo, int pageSize) throws Exception {
	PageUtil page = new PageUtil(pageNo, pageSize);
	List<Action> actions = actionMapper.queryByParentId(parentId,page.getPageOfset(), pageSize);
	return actions;
}

@Override
public Integer countByParentId(int parentId) throws Exception {
	int count = actionMapper.countByParentId(parentId);
	return count;
}


	
	

	
	
}
