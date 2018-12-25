package great.mapper;

import java.util.List;
import java.util.Map;

import great.bean.Menu;

public interface MenuMapper {
	// 新增加菜单
	public int addMenu(Menu menu);

	public int addMenu();

	// 查找所有一级菜单
	public List<Menu> queryFirstMenu();

	// 查询所有菜单
	public List<Menu> queryAllMenu(Menu menu);

	// 删除菜单
	public int delMenu(int menuId);

	// 根据Id查找菜单
	public Menu queryMenuById(int menuId);

	// 更新菜单
	public int updateMenu(Menu menu);

	// 批量删除菜单
	public int delMenuAll(Map<String, Object> map);

	// 根据菜单父Id查找所有下面的子菜单
	public List<Integer> queryMenuIdByParentId(int menuId);

}
