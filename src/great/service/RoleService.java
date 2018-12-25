package great.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import great.bean.Role;
import great.mapper.RoleLimitsMapper;
import great.mapper.RoleMapper;

@Service
public class RoleService
{
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RoleLimitsMapper roleLimitsMapper;

	// 添加角色
	public boolean addRole(Role role)
	{
		return roleMapper.addRole(role) > 0;
	}

	// 查找角色
	public List<Role> queryRole(Role role)
	{
		return roleMapper.queryRole(role);
	}

	// 删除角色
	@Transactional
	public boolean delRole(int roleId)
	{

		// 删除角色中间表的数据
		roleLimitsMapper.delRoleLimitsByRoleId(roleId);
		
		// 删除角色
		return roleMapper.delRole(roleId) > 0;
	}

	// 修改角色名称
	public boolean updateRoleName(Role role)
	{
		return roleMapper.updateRoleName(role) > 0;
	}
	// 批量删除角色
	@Transactional
	public boolean delAllRole(Map<String, Object> map)
	{
		// 批量删除多个角色在角色中间表里拥有的菜单
		roleLimitsMapper.delRoleLimitsByRoleList(map);
		
		// 批量删除角色
		return roleMapper.delAllRole(map) > 0;
	}
}
