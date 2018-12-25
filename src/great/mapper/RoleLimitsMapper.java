package great.mapper;

import java.util.List;
import java.util.Map;

import great.bean.Menu;
import great.bean.RoleLimits;

public interface RoleLimitsMapper {
	// 根据角色Id查找角色权限
	public List<RoleLimits> queryLimitsByRoleId(int roleId);

	// 批量插入
	public int addRoleLimits(Map<String, Object> map);

	// 删除角色权限
	public int delRoleLimitsByRoleId(int roleId);

	// 根据角色Id查找所有的菜单
	public List<Menu> queryMenuByRoleId(int roleId);

	// 根据角色Id批量删除多个角色拥有的菜单
	public int delRoleLimitsByRoleList(Map<String, Object> map);

	// 根据菜单Id批量删除多个角色拥有的菜单
	public int delRoleLimitsByMenuList(Map<String, Object> map);
}
