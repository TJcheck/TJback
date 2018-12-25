package great.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import great.bean.Admin;
import great.bean.Menu;
import great.bean.Role;
import great.mapper.AdminMapper;
import great.mapper.MenuMapper;
import great.mapper.RoleMapper;

@Service
public class AdminService {
	@Autowired
	AdminMapper adminMapper;

	// 注入角色Dao映射器
	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private MenuMapper menuMapper;

	// 用户登录
	public Admin adminLogin(Admin admin) {
		return adminMapper.adminLogin(admin);
	}

	// 重置密码
	public boolean updateAdminPwd(Admin admin) {
		return adminMapper.updateAdminPwd(admin) > 0;
	}

	// 添加角色
	public boolean addRole(Role role) {
		return roleMapper.addRole(role) > 0;
	}

	// 查找角色
	public List<Role> queryRole(Role role) {
		return roleMapper.queryRole(role);
	}

	// 删除角色
	public boolean delRole(int roleId) {
		return roleMapper.delRole(roleId) > 0;
	}

	// 修改角色名称
	public boolean updateRoleName(Role role) {
		return roleMapper.updateRoleName(role) > 0;
	}

	// 添加菜单
	public boolean addMenu(Menu menu) {
		return menuMapper.addMenu() > 0;
	}

	// 查找所有菜单
	public List<Menu> queryFirstMenu() {
		return null;
	}

}
