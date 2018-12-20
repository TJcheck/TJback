package great.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import great.bean.Role;
import great.mapper.RoleMapper;

@Service
public class RoleService
{
	@Autowired
	private RoleMapper roleMapper;

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
	public boolean delRole(int roleId)
	{
		return roleMapper.delRole(roleId) > 0;
	}

	// 修改角色名称
	public boolean updateRoleName(Role role)
	{
		return roleMapper.updateRoleName(role) > 0;
	}

}
