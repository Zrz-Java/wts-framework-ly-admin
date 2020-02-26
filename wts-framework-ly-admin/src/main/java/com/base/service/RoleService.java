package com.base.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import com.base.empty.Role;
import com.base.empty.RoleUser;
import com.base.empty.User;
import com.base.util.context.PageModel;

public interface RoleService {

	public PageModel<Role> list(User user, String searchValue, Integer pageNo, Integer pageSize) throws Exception;

	public PageModel<Role> list(User user, Map<String, Object> searchValues, Integer pageNo, Integer pageSize) throws Exception;

	public Role find(User user, Serializable id) throws Exception;

	public Role save(User user, Role role) throws Exception;

	public Role update(User user, Role role) throws Exception;

	public boolean delete(User user, Role role) throws Exception;

	public void addRoleUser(User user, String roleId, String userIds) throws Exception;

	public boolean deleteRoleUsers(User user, String roleId, String userIds) throws Exception;

	public PageModel<RoleUser> listRoleUser(User user, String roleId, String searchValue, Integer pageNo, Integer pageSize) throws Exception;

	public List<Role> roleTree(User user) throws Exception;

}
