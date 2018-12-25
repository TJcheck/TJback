package great.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import great.bean.Menu;
import great.mapper.MenuMapper;
import great.mapper.RoleLimitsMapper;

@Service
public class MenuService {
	@Autowired
	private MenuMapper menuMapper;

	@Autowired
	private RoleLimitsMapper roleLimitsMapper;

	// 添加菜单
	public boolean addMenu(Menu menu) {
		return menuMapper.addMenu(menu) > 0;
	}

	// 查找一级菜单
	public List<Menu> queryFirstMenu() {
		return menuMapper.queryFirstMenu();
	}

	// 查询所有菜单
	public List<Menu> queryAllMenu(Menu menu) {
		return menuMapper.queryAllMenu(menu);
	}

	// 删除菜单
	public boolean delMenu(int menuId) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 查找该菜单下的子菜单
		List<Integer> menList = menuMapper.queryMenuIdByParentId(menuId);
		menList.add(menuId);
		/*
		 * for (int i = 0; i < menList.size(); i++) { System.out.println("i=" +
		 * menList.get(i)); }
		 */
		map.put("menuIdArray", menList);
		// 删除菜单中间表所有的记录数
		roleLimitsMapper.delRoleLimitsByMenuList(map);
		// 删除菜单表中的菜单
		return menuMapper.delMenu(menuId) > 0;
	}

	// 根据Id查找菜单
	public Menu queryMenuById(int menuId) {
		return menuMapper.queryMenuById(menuId);
	}

	// 更新菜单
	public boolean updateMenu(Menu menu) {
		return menuMapper.updateMenu(menu) > 0;
	}

	// 批量删除菜单
	public boolean delMenuAll(Map<String, Object> map) {
		int[] menuIdArray = (int[]) map.get("menuIdArray");
		List<Integer> menList = new ArrayList<Integer>();
		// 把所有菜单id下的子菜单查出来
		for (int i = 0; i < menuIdArray.length; i++) {
			menList.add(menuIdArray[i]);
			List<Integer> menListTemp = menuMapper.queryMenuIdByParentId(menuIdArray[i]);
			if (menListTemp != null) {
				menList.addAll(menListTemp);
			}
		}
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("menuIdArray", menList);

		for (int i = 0; i < menList.size(); i++) {
			System.out.println("i=" + menList.get(i));
		}
		// 批量删除菜单中间表所有的记录数
		roleLimitsMapper.delRoleLimitsByMenuList(map1);
		// 批量删除菜单表中的菜单
		return menuMapper.delMenuAll(map) > 0;
	}

}
