package great.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import great.bean.Role;

public interface RoleMapper
{
	// 添加角色
	public int addRole(Role role);

	// 查找所有角色
	public List<Role> queryRole(Role role);

	// 删除角色
	public int delRole(int roleId);

	// 修改角色名称
	public int updateRoleName(Role role);
	

}
