package great.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import great.bean.Menu;
import great.mapper.MenuMapper;

@Service
public class MenuService
{
	@Autowired
	private MenuMapper menuMapper;

	// 添加菜单
	public boolean addMenu(Menu menu)
	{
		return menuMapper.addMenu(menu) > 0;
	}

	// 查找一级菜单
	public List<Menu> queryFirstMenu()
	{
		return menuMapper.queryFirstMenu();
	}

	// 查询所有菜单
	public List<Menu> queryAllMenu(Menu menu)
	{
		return menuMapper.queryAllMenu(menu);
	}

	// 删除菜单
	public boolean delMenu(int menuId)
	{
		return menuMapper.delMenu(menuId) > 0;
	}

	// 根据Id查找菜单
	public Menu queryMenuById(int menuId)
	{
		return menuMapper.queryMenuById(menuId);
	}

	// 更新菜单
	public boolean updateMenu(Menu menu)
	{
		return menuMapper.updateMenu(menu) > 0;
	}

	// 批量删除菜单
	public int delMenuAll(Map<String, Object> map)
	{
		return menuMapper.delMenuAll(map);
	}
}
