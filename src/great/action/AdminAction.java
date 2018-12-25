package great.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import great.bean.Admin;
import great.bean.Combo;
import great.bean.Company;
import great.bean.Dept;
import great.bean.Examine;
import great.bean.Item;
import great.bean.Menu;
import great.bean.PageInfo;
import great.bean.Project;
import great.bean.ProjectCombo;
import great.bean.ProjectItem;
import great.bean.Role;
import great.bean.RoleLimits;
import great.bean.ztreeNode;
import great.service.AdminService;
import great.service.ComboService;
import great.service.CompanyService;
import great.service.DeptService;
import great.service.ExamineService;
import great.service.ItemService;
import great.service.MenuService;
import great.service.ProjectService;
import great.service.RoleLimitsService;
import great.service.RoleService;

@Controller
@RequestMapping(value = "/admin")
public class AdminAction {
	@Autowired
	private AdminService adminService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private MenuService menuService;

	@Autowired
	private RoleLimitsService roleLimitsMapper;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private DeptService deptService;// 科室设置

	@Autowired
	private ItemService itemService;// 细项设置

	@Autowired
	private ProjectService projectService;// 项目设置

	@Autowired
	private ComboService comboService;// 套餐设置

	@Autowired
	private ExamineService examineService;

	// 套餐配置
	// 项目更新
	@RequestMapping(value = "/updateCombo.action")
	@ResponseBody
	public String updateCombo(@RequestBody Combo combo) {
		if (comboService.updateCombo(combo)) {
			return "success";
		}
		return "failed";
	}

	// 套餐配置
	// 批量删除套餐
	@RequestMapping(value = "/delCombos.action")
	@ResponseBody
	public String delCombos(@RequestParam(value = "delboxs[]") int[] comboIds) {
		if (comboService.delCombos(comboIds)) {
			return "success";
		}
		return "failed";
	}

	// 查询项目项目树
	@RequestMapping(value = "/queryProjects.action")
	@ResponseBody
	public List<Project> queryProjects(int comboId) {
		// 查询所有细项
		List<Project> projects = projectService.queryProject(new Project());
		List<ProjectCombo> proCombos = null;
		// 根据Id查询中间表
		if (comboId != 0) {
			ProjectCombo proCombo = new ProjectCombo();
			proCombo.setComboId(comboId);
			proCombos = comboService.queryProjects(proCombo);
			// 查询细项中间表
			if (proCombos.size() != 0) {
				for (Project project : projects) {
					project.setChecked(false);
					for (ProjectCombo proCombo1 : proCombos) {
						if (proCombo1.getProjectId() == project.getProjectId()) {
							project.setChecked(true);
						}
					}
				}
			}
		}

		return projects;
	}

	// 添加项目
	@ResponseBody
	@RequestMapping(value = "/addCombo.action")
	public String addCombo(@RequestBody Combo combo) {
		boolean flag = comboService.addCombo(combo);
		// 插入中间表
		if (flag) {
			return "success";
		}
		return "failed";
	}

	//跳转到更新套餐页面
	@RequestMapping(value = "/updateComboPage.action")
	public ModelAndView updateComboPage(int comboId,int currentPage)
	{
		ModelAndView mav = new ModelAndView();
		Combo combo = new Combo();
		combo.setComboId(comboId);
		List<Combo> combos = comboService.queryCombo(combo);
		mav.addObject("combo", combos.get(0));
		mav.addObject("currentPage", currentPage);
		mav.setViewName("/back/combo_update");
		return mav;
	}

	// 跳转到套餐新增页
	@RequestMapping(value = "/addComboPage.action")
	public String addComboPage() {
		return "/back/combo_add";
	}

	//删除套餐
	@RequestMapping(value = "/delCombo.action")
	@ResponseBody
	public String delCombo(int comboId) {
		if (comboService.delCombo(comboId)) {
			return "success";
		}
		return "failed";
	}

	// 跳转到套餐管理页面
	@RequestMapping(value = "/comboManagerPage.action")
	public ModelAndView comboManagerPage() {
		ModelAndView mav = new ModelAndView();
		// 第一次跳转当前页数设置为1
		int currentPage = 1;
		// 分页工具分页
		Page<Object> page = PageHelper.startPage(currentPage, 5);
		// 查询项目数据
		Combo combo = new Combo();
		List<Combo> comboList = comboService.queryCombo(combo);
		currentPage = page.getPageNum();// 当前页数
		int totalPage = page.getPages();// 获得总页数
		int totalNum = (int) page.getTotal();// 总记录数
		// 如果什么都没有查到，当前页变为0
		if (totalNum == 0) {
			currentPage = 0;
		}
		Map<String, Object> data = new HashMap<String, Object>();// 查询结果数据
		data.put("comboList", comboList);
		PageInfo pageInfo = new PageInfo(currentPage, totalPage, totalNum, data);
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("/back/combo_manage");
		return mav;
	}

	// ajax分页查询项目信息
	@RequestMapping(value = "/comboListPaging.action")
	@ResponseBody
	public PageInfo comboListPaging(int currentPage, String comboName) {
		// 分页工具分页每页五条
		Page<Object> page = PageHelper.startPage(currentPage, 5);
		// 查询角色数据
		Combo combo = new Combo();
		if (comboName != null && !comboName.equals("")) {
			combo.setComboName(comboName);
		}
		List<Combo> comboList = comboService.queryCombo(combo);
		currentPage = page.getPageNum();// 当前页数
		int totalPage = page.getPages();// 获得总页数
		int totalNum = (int) page.getTotal();// 总记录数
		// 如果什么都没有查到，当前页变为0
		if (totalNum == 0) {
			currentPage = 0;
		}
		Map<String, Object> data = new HashMap<String, Object>();// 查询结果数据
		data.put("comboList", comboList);
		PageInfo pageInfo = new PageInfo(currentPage, totalPage, totalNum, data);
		return pageInfo;
	}

	// 项目配置
	// 项目更新
	@RequestMapping(value = "/updateProject.action")
	@ResponseBody
	public String updateProject(@RequestBody Project project) {
		if (projectService.updateProject(project)) {
			return "success";
		}
		return "failed";
	}

	// 查询项目细项树
	@RequestMapping(value = "/queryItems.action")
	@ResponseBody
	public List<Item> queryItems(int projectId) {
		// 查询所有细项
		List<Item> items = itemService.queryItem(new Item());
		List<ProjectItem> proItems = null;
		// 根据Id查询中间表
		if (projectId != 0) {
			ProjectItem proItem = new ProjectItem();
			proItem.setProjectId(projectId);
			proItems = projectService.queryItems(proItem);
			// 查询细项中间表
			if (proItems.size() != 0) {
				for (Item item : items) {
					item.setChecked(false);
					for (ProjectItem projectItem : proItems) {
						if (projectItem.getItemId() == item.getItemId()) {
							item.setChecked(true);
						}
					}
				}
			}
		}

		return items;
	}

	// 添加项目
	@ResponseBody
	@RequestMapping(value = "/addProject.action")
	public String addProject(@RequestBody Project project) {
		boolean flag = projectService.addProject(project);
		// 插入中间表
		if (flag) {
			return "success";
		}
		return "failed";
	}

	// 跳转到更新项目页面
	@RequestMapping(value = "/updateProjectPage.action")
	public ModelAndView updateProjectPage(int projectId) {
		ModelAndView mav = new ModelAndView();
		Project project = new Project();
		project.setProjectId(projectId);
		List<Project> projects = projectService.queryProject(project);
		List<Dept> deptList = deptService.queryDept(new Dept());
		mav.addObject("project", projects.get(0));
		mav.addObject("deptList", deptList);
		mav.setViewName("/back/project_update");
		return mav;
	}

	// 跳转到项目新增页
	@RequestMapping(value = "/addProjectPage.action")
	public String addProjectPage(HttpServletRequest req) {
		List<Dept> deptList = deptService.queryDept(new Dept());
		req.setAttribute("deptList", deptList);
		return "/back/project_add";
	}

	// 删除项目
	@RequestMapping(value = "/delProject.action")
	@ResponseBody
	public String delProject(int projectId) {
		if (projectService.delProject(projectId)) {
			return "success";
		}
		return "failed";
	}

	// 跳转到项目管理页面
	@RequestMapping(value = "/projectManagerPage.action")
	public ModelAndView projectManagerPage() {
		ModelAndView mav = new ModelAndView();
		// 第一次跳转当前页数设置为1
		int currentPage = 1;
		// 分页工具分页
		Page<Object> page = PageHelper.startPage(currentPage, 5);
		// 查询项目数据
		Project project = new Project();
		List<Project> projectList = projectService.queryProject(project);
		currentPage = page.getPageNum();// 当前页数
		int totalPage = page.getPages();// 获得总页数
		int totalNum = (int) page.getTotal();// 总记录数
		// 如果什么都没有查到，当前页变为0
		if (totalNum == 0) {
			currentPage = 0;
		}
		Map<String, Object> data = new HashMap<String, Object>();// 查询结果数据
		data.put("projectList", projectList);
		PageInfo pageInfo = new PageInfo(currentPage, totalPage, totalNum, data);
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("/back/project_manage");
		return mav;
	}

	// ajax分页查询项目信息
	@RequestMapping(value = "/projectListPaging.action")
	@ResponseBody
	public PageInfo projectListPaging(int currentPage, String projectName) {
		// 分页工具分页每页五条
		Page<Object> page = PageHelper.startPage(currentPage, 5);
		// 查询角色数据
		Project project = new Project();
		if (projectName != null && !projectName.equals("")) {
			project.setProjectName(projectName);
		}
		List<Project> projectList = projectService.queryProject(project);
		currentPage = page.getPageNum();// 当前页数
		int totalPage = page.getPages();// 获得总页数
		int totalNum = (int) page.getTotal();// 总记录数
		// 如果什么都没有查到，当前页变为0
		if (totalNum == 0) {
			currentPage = 0;
		}
		Map<String, Object> data = new HashMap<String, Object>();// 查询结果数据
		data.put("projectList", projectList);
		PageInfo pageInfo = new PageInfo(currentPage, totalPage, totalNum, data);
		return pageInfo;
	}

	// 细项配置
	// 更新细项
	@ResponseBody
	@RequestMapping(value = "/updateItem.action")
	public String updateItem(@RequestBody Item item) {
		if (itemService.updateItem(item)) {
			return "success";
		}
		return "failed";
	}

	// 跳转到更新细项页面
	@RequestMapping(value = "/updateItemPage.action")
	public ModelAndView updateItemPage(int itemId) {
		ModelAndView mav = new ModelAndView();
		Item item = new Item();
		item.setItemId(itemId);
		List<Item> items = itemService.queryItem(item);
		mav.addObject("item", items.get(0));
		mav.setViewName("/back/item_update");
		return mav;
	}

	// 添加细项
	@ResponseBody
	@RequestMapping(value = "/addItem.action")
	public String addItem(@RequestBody Item item) {
		if (itemService.addItem(item)) {
			return "success";
		}
		return "failed";
	}

	// 跳转到细项新增页
	@RequestMapping(value = "/addItemPage.action")
	public String addItemPage() {
		return "/back/item_add";
	}

	// 删除细项
	@RequestMapping(value = "/delItem.action")
	@ResponseBody
	public String delItem(int itemId) {
		if (itemService.delItem(itemId)) {
			return "success";
		} else {
			return "failed";
		}
	}

	// 跳转到细项管理页面
	@RequestMapping(value = "/itemManagerPage.action")
	public ModelAndView itemManagerPage() {
		ModelAndView mav = new ModelAndView();
		// 第一次跳转当前页数设置为1
		int currentPage = 1;
		// 分页工具分页
		Page<Object> page = PageHelper.startPage(currentPage, 5);
		// 查询细项数据
		Item item = new Item();
		List<Item> itemList = itemService.queryItem(item);// 空?
		currentPage = page.getPageNum();// 当前页数
		int totalPage = page.getPages();// 获得总页数
		int totalNum = (int) page.getTotal();// 总记录数
		// 如果什么都没有查到，当前页变为0
		if (totalNum == 0) {
			currentPage = 0;
		}
		Map<String, Object> data = new HashMap<String, Object>();// 查询结果数据
		data.put("itemList", itemList);
		PageInfo pageInfo = new PageInfo(currentPage, totalPage, totalNum, data);
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("/back/item_manage");
		return mav;
	}

	// ajax分页查询细项信息
	@RequestMapping(value = "/itemListPaging.action")
	@ResponseBody
	public PageInfo itemListPaging(int currentPage, String itemName) {
		// 分页工具分页每页五条
		Page<Object> page = PageHelper.startPage(currentPage, 5);
		// 查询角色数据
		Item item = new Item();
		if (itemName != null && !itemName.equals("")) {
			item.setItemName(itemName);
		}
		List<Item> itemList = itemService.queryItem(item);
		currentPage = page.getPageNum();// 当前页数
		int totalPage = page.getPages();// 获得总页数
		int totalNum = (int) page.getTotal();// 总记录数
		// 如果什么都没有查到，当前页变为0
		if (totalNum == 0) {
			currentPage = 0;
		}
		Map<String, Object> data = new HashMap<String, Object>();// 查询结果数据
		data.put("itemList", itemList);
		PageInfo pageInfo = new PageInfo(currentPage, totalPage, totalNum, data);
		return pageInfo;
	}

	// 科室设置
	// 更新科室
	@ResponseBody
	@RequestMapping(value = "/updateDeptName.action")
	public String updateDeptName(@RequestBody Dept dept) {
		if (deptService.updateDeptName(dept)) {
			return "success";
		}
		return "failed";
	}

	// 跳转到更新科室
	@RequestMapping(value = "/updateDeptPage.action")
	public ModelAndView updateDeptPage(HttpServletRequest req, String deptId, String deptName) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("deptId", deptId);
		mav.addObject("deptName", deptName);
		mav.setViewName("/back/dept_update");
		return mav;
	}

	// 删除科室
	@RequestMapping(value = "/delDept.action")
	@ResponseBody
	public String delDept(String deptId) {
		int deldeptId = Integer.parseInt(deptId);
		if (deptService.delDept(deldeptId)) {
			return "success";
		} else {
			return "failed";
		}
	}

	// 添加科室
	@ResponseBody
	@RequestMapping(value = "/addDept.action")
	public Map<String, Object> addDept(String deptName) {
		System.out.println(deptName);
		Map<String, Object> map = new HashMap<String, Object>();
		if (deptService.addDept(deptName)) {
			map.put("msg", "success");
		} else {
			map.put("msg", "failed");
		}
		return map;
	}

	// 跳转到新增科室页面
	@RequestMapping(value = "/addDeptPage.action")
	public String addDeptPage() {
		return "/back/dept_add";
	}

	// 跳转到科室管理页面
	@RequestMapping(value = "/deptManagerPage.action")
	public ModelAndView deptManagerPage() {
		ModelAndView mav = new ModelAndView();
		// 第一次跳转当前页数设置为1
		int currentPage = 1;
		// 分页工具分页
		Page<Object> page = PageHelper.startPage(currentPage, 5);
		// 查询科室数据
		Dept dept = new Dept();
		List<Dept> deptList = deptService.queryDept(dept);
		currentPage = page.getPageNum();// 当前页数
		int totalPage = page.getPages();// 获得总页数
		int totalNum = (int) page.getTotal();// 总记录数
		// 如果什么都没有查到，当前页变为0
		if (totalNum == 0) {
			currentPage = 0;
		}
		Map<String, Object> data = new HashMap<String, Object>();// 查询结果数据
		data.put("deptList", deptList);
		PageInfo pageInfo = new PageInfo(currentPage, totalPage, totalNum, data);
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("/back/dept_manage");
		return mav;
	}

	// ajax分页查询科室信息
	@RequestMapping(value = "/deptListPaging.action")
	@ResponseBody
	public PageInfo deptListPaging(int currentPage, String deptName) {
		// 分页工具分页每页五条
		Page<Object> page = PageHelper.startPage(currentPage, 5);
		// 查询角色数据
		Dept dept = new Dept();
		if (deptName != null && !deptName.equals("")) {
			dept.setDeptName(deptName);
		}
		List<Dept> deptList = deptService.queryDept(dept);
		currentPage = page.getPageNum();// 当前页数
		int totalPage = page.getPages();// 获得总页数
		int totalNum = (int) page.getTotal();// 总记录数
		// 如果什么都没有查到，当前页变为0
		if (totalNum == 0) {
			currentPage = 0;
		}
		Map<String, Object> data = new HashMap<String, Object>();// 查询结果数据
		data.put("deptList", deptList);
		PageInfo pageInfo = new PageInfo(currentPage, totalPage, totalNum, data);
		return pageInfo;
	}

	// 跳转到角色管理页面
	@RequestMapping(value = "/roleManagerPage.action")
	public ModelAndView roleManagerPage(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		// 第一次跳转当前页数设置为1
		int currentPage = 1;
		// 分页工具分页
		Page<Object> page = PageHelper.startPage(currentPage, 5);
		// 查询角色数据
		Role role = new Role();
		List<Role> roleList = roleService.queryRole(role);
		currentPage = page.getPageNum();// 当前页数
		int totalPage = page.getPages();// 获得总页数
		int totalNum = (int) page.getTotal();// 总记录数
		// 如果什么都没有查到，当前页变为0
		if (totalNum == 0) {
			currentPage = 0;
		}
		Map<String, Object> data = new HashMap<String, Object>();// 查询结果数据
		data.put("roleList", roleList);

		PageInfo pageInfo = new PageInfo(currentPage, totalPage, totalNum, data);

		req.setAttribute("pageInfo", pageInfo);
		mav.setViewName("/back/role_manage");
		return mav;
	}

	// 跳转到角色增加页面
	@RequestMapping(value = "/addRolePage.action")
	public ModelAndView addRolePage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/back/role_add");
		return mav;
	}

	// 添加角色
	@RequestMapping(value = "/addRole.action", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String addRole(@RequestBody Role role) {
		if (roleService.addRole(role)) {
			return "success";
		} else {
			return "failed";
		}
	}

	// 删除角色
	@RequestMapping(value = "/delRole.action")
	@ResponseBody
	public String delRole(@RequestParam(value = "roleId") String roleId) {
		int delRoleId = Integer.parseInt(roleId);
		if (roleService.delRole(delRoleId)) {
			return "success";
		} else {
			return "failed";
		}
	}

	// 跳转到更新角色的页面
	@RequestMapping(value = "/updateRolePage.action")
	public ModelAndView updateRolePage(HttpServletRequest req, @RequestParam(value = "roleName") String roleName,
			@RequestParam(value = "roleId") String roleId) {
		req.setAttribute("roleName", roleName);
		req.setAttribute("roleId", roleId);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/back/role_update");
		return mav;
	}

	// 更新角色名字
	@RequestMapping(value = "/updateRoleName.action")
	@ResponseBody
	public String updateRoleName(@RequestBody Role role) {
		if (roleService.updateRoleName(role)) {
			return "success";
		} else {
			return "failed";
		}
	}

	// 分页查询角色信息
	@RequestMapping(value = "/roleListPaging.action")
	@ResponseBody
	public PageInfo roleListPaging(@RequestParam(value = "currentPage") int currentPage,
			@RequestParam(value = "roleName") String roleName) {
		// 分页工具分页
		Page<Object> page = PageHelper.startPage(currentPage, 5);
		// 查询角色数据
		Role role = new Role();
		if (roleName != null && !roleName.equals("")) {
			role.setRoleName(roleName);
		}
		List<Role> roleList = roleService.queryRole(role);
		currentPage = page.getPageNum();// 当前页数
		int totalPage = page.getPages();// 获得总页数
		int totalNum = (int) page.getTotal();// 总记录数
		// 如果什么都没有查到，当前页变为0
		if (totalNum == 0) {
			currentPage = 0;
		}
		Map<String, Object> data = new HashMap<String, Object>();// 查询结果数据
		data.put("roleList", roleList);
		PageInfo pageInfo = new PageInfo(currentPage, totalPage, totalNum, data);
		return pageInfo;
	}

	// 跳转到添加菜单页面
	@RequestMapping(value = "/addMenuPage.action")
	public ModelAndView addMenuPage(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		List<Menu> menuList = menuService.queryFirstMenu();
		req.setAttribute("menuList", menuList);
		mav.setViewName("/back/menu_add");
		return mav;
	}

	// 添加菜单
	@RequestMapping(value = "/addMenu.action")
	@ResponseBody
	public String addMenu(@RequestBody Menu menu) {
		if (menuService.addMenu(menu)) {
			return "success";
		} else {
			return "failed";
		}

	}

	// 跳转到菜单管理页面
	@RequestMapping(value = "/menuManagePage.action")
	public ModelAndView queryAllMenu(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		// 第一次跳转当前页数设置为1
		int currentPage = 1;
		// 分页工具分页
		Page<Object> page = PageHelper.startPage(currentPage, 5);
		// 查询角色数据
		Menu menu = new Menu();
		List<Menu> menuList = menuService.queryAllMenu(menu);
		currentPage = page.getPageNum();// 当前页数
		int totalPage = page.getPages();// 获得总页数
		int totalNum = (int) page.getTotal();// 总记录数
		// 如果什么都没有查到，当前页变为0
		if (totalNum == 0) {
			currentPage = 0;
		}
		Map<String, Object> data = new HashMap<String, Object>();// 查询结果数据

		data.put("menuList", menuList);

		PageInfo pageInfo = new PageInfo(currentPage, totalPage, totalNum, data);

		req.setAttribute("pageInfo", pageInfo);
		mav.setViewName("/back/menu_manage");
		return mav;
	}

	// 删除菜单
	@RequestMapping(value = "/delMenu.action")
	@ResponseBody
	public String delMenu(@RequestParam(value = "menuId") int menuId) {
		if (menuService.delMenu(menuId)) {
			return "success";
		} else {
			return "failed";
		}

	}

	// 分页查询菜单信息
	@RequestMapping(value = "/menuListPaging.action")
	@ResponseBody
	public PageInfo menuListPaging(@RequestParam(value = "currentPage") int currentPage,
			@RequestParam(value = "menuName") String menuName) {
		// 分页工具分页
		Page<Object> page = PageHelper.startPage(currentPage, 5);
		// 查询角色数据
		Menu menu = new Menu();
		if (menuName != null && !menuName.equals("")) {
			menu.setMenuName(menuName);
		}
		List<Menu> menuList = menuService.queryAllMenu(menu);
		currentPage = page.getPageNum();// 当前页数
		int totalPage = page.getPages();// 获得总页数
		int totalNum = (int) page.getTotal();// 总记录数
		// 如果什么都没有查到，当前页变为0
		if (totalNum == 0) {
			currentPage = 0;
		}
		Map<String, Object> data = new HashMap<String, Object>();// 查询结果数据
		data.put("menuList", menuList);
		PageInfo pageInfo = new PageInfo(currentPage, totalPage, totalNum, data);
		return pageInfo;
	}

	// 跳转到更新菜单页面
	@RequestMapping(value = "/updateMenuPage.action")
	public ModelAndView updateMenuPage(HttpServletRequest req, @RequestParam(value = "menuId") int menuId) {
		Menu menu = new Menu();
		// 查询要修改的菜单信息
		menu = menuService.queryMenuById(menuId);
		// 查询所有的一级菜单
		List<Menu> menuList = menuService.queryFirstMenu();
		req.setAttribute("menu", menu);
		req.setAttribute("menuList", menuList);
		/*
		 * for (Menu menu2 : menuList) { System.out.println(menu2.getMenuId());
		 * System.out.println(menu2.getMenuName()); }
		 */
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/back/menu_update");
		return mav;

	}

	// 更新菜单
	@RequestMapping(value = "/updateMenu.action")
	@ResponseBody
	public String updateMenu(@RequestBody Menu menu) {
		if (menuService.updateMenu(menu)) {
			return "success";
		} else {
			return "fail";
		}
	}

	// 返回角色权限数据给JTree显示
	@RequestMapping(value = "/roleMenuInfo.action", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String roleMenuInfo(@RequestParam(value = "roleId") int roleId) {
		/* 查询所有的菜单 */
		Menu menu = new Menu();
		List<Menu> menuList = menuService.queryAllMenu(menu);

		/* 查询角色拥有的菜单 */
		// 1、查询角色拥有的权限
		List<RoleLimits> result = roleLimitsMapper.queryLimitsByRoleId(roleId);
		List<Integer> roleMenuList = new ArrayList<>();
		for (int i = 0; i < result.size(); i++) {
			// 2、通过角色权限表里拥有的菜单查找该角色下所有的菜单
			int menuId = result.get(i).getMenuId();
			roleMenuList.add(menuId);
		}

		/* 得到ztree要用的菜单节点数据 */
		ArrayList<ztreeNode> nodeList = new ArrayList<>();
		for (int i = 0; i < menuList.size(); i++) {
			ztreeNode node = new ztreeNode();
			// 1、设置菜单ID
			int menuId = menuList.get(i).getMenuId();
			node.setMenuId(menuId);
			// 2、设置菜单父ID
			int parentId = menuList.get(i).getParentId();
			node.setParentId(parentId);
			// 3、设置菜单名
			String menuName = menuList.get(i).getMenuName();
			node.setMenuName(menuName);
			for (int j = 0; j < roleMenuList.size(); j++) {
				int roleMenuId = roleMenuList.get(j);
				if (roleMenuId == menuId) {
					// 4、设置该菜单有没有被选中
					node.setSelected(true);
					break;
				} else {
					node.setSelected(false);
				}
			}
			nodeList.add(node);
		}
		ObjectMapper mapper = new ObjectMapper();
		String str = null;
		try {
			str = mapper.writeValueAsString(nodeList);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}

	// 跳转到权限管理页面
	@RequestMapping(value = "/roleLimitsMangePage.action")
	public ModelAndView roleLimitsMange() {
		ModelAndView mav = new ModelAndView();
		Role role = new Role();
		List<Role> roleList = roleService.queryRole(role);
		mav.addObject("roleList", roleList);
		mav.setViewName("/back/role_limit");
		return mav;
	}

	// 更新角色权限
	@RequestMapping(value = "/updateRoleLimits")
	@ResponseBody
	public String updateRoleLimits(@RequestParam(value = "roleId") int roleId,
			@RequestParam(value = "roleMenuId") String roleMenuId) {
		String[] menuId = roleMenuId.split(":");
		int[] menuIdArray = new int[menuId.length];
		for (int i = 0; i < menuId.length; i++) {
			menuIdArray[i] = Integer.parseInt(menuId[i]);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		map.put("menuIdArray", menuIdArray);
		if (roleLimitsMapper.addRoleLimits(map)) {
			return "success";
		} else {
			return "fail";
		}

	}

	// 登录成功以后动态跳转的主页并且生成动态菜单，这里假设一个登录者的角色Id
	@RequestMapping(value = "/adminMainPage.action")
	public ModelAndView adminMainPage(HttpServletRequest req)
	{
		ModelAndView mav = new ModelAndView();
		Admin adminLogin = (Admin) req.getSession().getAttribute("adminLogin");
		int roleId = adminLogin.getRoleId();
		List<Menu> menuList = roleLimitsMapper.queryMenuByRoleId(roleId);
		mav.setViewName("/back/admin_main");
		ObjectMapper mapper = new ObjectMapper();
		String str = null;
		try
		{
			str = mapper.writeValueAsString(menuList);
		} catch (JsonProcessingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.addObject("menuList", str);
		mav.addObject("adminLogin", adminLogin);
		return mav;
	}

	// 批量删除菜单
	@RequestMapping(value = "/delMenuAll.action")
	@ResponseBody
	public String delMenuAll(@RequestParam(value = "menuId[]") int[] menuIdArray) {
		for (int i : menuIdArray) {
			System.out.println(i);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("menuIdArray", menuIdArray);

		if (menuService.delMenuAll(map)) {
			System.out.println("成功");
			return "success";
		} else {
			return "fail";
		}

	}

	// 批量删除角色
	@RequestMapping(value = "/delAllRole.action")
	@ResponseBody
	public String delAllRole(@RequestParam(value = "roleId[]") int[] roleIdArray) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleIdArray", roleIdArray);

		if (roleService.delAllRole(map)) {
			return "success";
		} else {
			return "fail";
		}
	}

	// 用户登录
	@RequestMapping(value = "/adminlogin.action")
	@ResponseBody
	public String adminlogin(@RequestParam(value = "userPwd") String adminPwd,
			@RequestParam(value = "userName") String adminName, HttpServletRequest req) {
		Admin admin = new Admin();
		admin.setAdminName(adminName);
		admin.setAdminPwd(adminPwd);
		Admin adminLogin = adminService.adminLogin(admin);
		if (adminLogin != null) {
			// 将用户存放到session里面
			HttpSession session = req.getSession();
			session.setAttribute("adminLogin", adminLogin);
			return "1";
		} else {
			return "3";
		}

	}

	// 用户退出
	@RequestMapping(value = "/adminExit.action")
	public ModelAndView adminExit(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = req.getSession();
		// 销毁session
		session.invalidate();
		mav.setViewName("/back/admin_login");
		return mav;
	}

	// 重置用户密码
	@RequestMapping(value = "/updateAdminPwd")
	@ResponseBody
	public String updateAdminPwd(@RequestParam(value = "userName") String adminName,
			@RequestParam(value = "userPwd") String adminPwd) {
		Admin admin = new Admin();
		admin.setAdminName(adminName);
		admin.setAdminPwd(adminPwd);
		if (adminService.updateAdminPwd(admin)) {
			return "1";
		} else {
			return "3";
		}
	}

	// 查询团体订单信息
	@RequestMapping(value = "/queryOrderInfo.action")
	public ModelAndView queryOrderInfo(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		// 第一次跳转当前页数设置为1
		int currentPage = 1;
		// 分页工具分页
		Page<Object> page = PageHelper.startPage(currentPage, 5);
		// 查询角色数据
		Company company = new Company();
		List<Company> companyList = companyService.queryOrderInfo(company);
		currentPage = page.getPageNum();// 当前页数
		int totalPage = page.getPages();// 获得总页数
		int totalNum = (int) page.getTotal();// 总记录数
		// 如果什么都没有查到，当前页变为0
		if (totalNum == 0) {
			currentPage = 0;
		}
		Map<String, Object> data = new HashMap<String, Object>();// 查询结果数据

		data.put("companyList", companyList);

		PageInfo pageInfo = new PageInfo(currentPage, totalPage, totalNum, data);

		req.setAttribute("pageInfo", pageInfo);
		mav.setViewName("/back/order_info");
		return mav;
	}

	// 分页查询团体订单信息
	@RequestMapping(value = "/queryOrderInfoPaging.action")
	@ResponseBody
	public PageInfo queryOrderInfoPaging(@RequestParam(value = "currentPage") int currentPage,
			@RequestParam(value = "companyName") String companyName) {
		// 分页工具分页
		Page<Object> page = PageHelper.startPage(currentPage, 5);
		// 查询角色数据
		Company company = new Company();
		if (companyName != null && !companyName.equals("")) {
			company.setCompanyName(companyName);
		}
		List<Company> companyList = companyService.queryOrderInfo(company);
		currentPage = page.getPageNum();// 当前页数
		int totalPage = page.getPages();// 获得总页数
		int totalNum = (int) page.getTotal();// 总记录数
		// 如果什么都没有查到，当前页变为0
		if (totalNum == 0) {
			currentPage = 0;
		}
		Map<String, Object> data = new HashMap<String, Object>();// 查询结果数据
		data.put("companyList", companyList);
		PageInfo pageInfo = new PageInfo(currentPage, totalPage, totalNum, data);
		return pageInfo;
	}

	// 团检单位对账时跳转到公司套餐项目信息界面
	@RequestMapping(value = "/queryCheckPro.action")
	public ModelAndView queryCheckPro(@RequestParam(value = "comboId") int comboId, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		List<ProjectCombo> projectComboList = comboService.queryCheckPro(comboId);
		req.setAttribute("projectComboList", projectComboList);
		mav.setViewName("/back/combo_info");
		return mav;
	}

	// 团检单位对账时跳转到公司个人信息界面
	@RequestMapping(value = "/queryExamineInfo.action")
	public ModelAndView queryExamineInfo(@RequestParam(value = "companyId") int companyId, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		List<Examine> peopleList = examineService.queryExamineInfo(companyId);
		req.setAttribute("peopleList", peopleList);
		mav.setViewName("/back/people_info");
		return mav;
	}
}
