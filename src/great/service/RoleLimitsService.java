package great.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import great.bean.Menu;
import great.bean.RoleLimits;
import great.mapper.RoleLimitsMapper;

@Service
public class RoleLimitsService
{
	@Autowired
	RoleLimitsMapper roleLimitsMapper;

	// 根据角色Id查找角色权限
	public List<RoleLimits> queryLimitsByRoleId(int roleId)
	{
		return roleLimitsMapper.queryLimitsByRoleId(roleId);
	}

	// 批量插入角色权限
	@Transactional(rollbackFor = Exception.class)
	public boolean addRoleLimits(Map<String, Object> map)
	{
		roleLimitsMapper.delRoleLimitsByRoleId((int) map.get("roleId"));
		roleLimitsMapper.addRoleLimits(map);
		return roleLimitsMapper.addRoleLimits(map) > 0;
	}

	// 根据角色Id查找所有的菜单
	public List<Menu> queryMenuByRoleId(int roleId)
	{
		return roleLimitsMapper.queryMenuByRoleId(roleId);

	}

}
