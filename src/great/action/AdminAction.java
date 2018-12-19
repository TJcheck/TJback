package great.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import great.bean.Role;
import great.service.AdminService;

@Controller
@RequestMapping(value = "/admin")
public class AdminAction
{
	@Autowired
	private AdminService adminService;

	// 跳转到角色管理页面
	@RequestMapping(value = "/roleManagerPage.action")
	public ModelAndView roleManagerPage(HttpServletRequest req)
	{
		ModelAndView mav = new ModelAndView();
		List<Role> roleList = adminService.queryRole();
		req.setAttribute("roleList", roleList);
		mav.setViewName("/back/role_manage");
		return mav;
	}

	// 跳转到角色增加页面
	@RequestMapping(value = "/addRolePage.action")
	public ModelAndView addRolePage()
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/back/role_add");
		return mav;
	}

	// 添加角色
	@RequestMapping(value = "/addRole.action", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String addRole(@RequestBody Role role)
	{
		if (adminService.addRole(role))
		{
			return "success";
		} else
		{
			return "failed";
		}
	}

	// 删除角色
	@RequestMapping(value = "/delRole.action")
	@ResponseBody
	public String delRole(@RequestParam(value = "roleId") String roleId)
	{
		int delRoleId = Integer.parseInt(roleId);
		if (adminService.delRole(delRoleId))
		{
			return "success";
		} else
		{
			return "failed";
		}
	}

	// 跳转到更新角色的页面
	@RequestMapping(value = "/updateRolePage.action")
	public ModelAndView updateRolePage(HttpServletRequest req, @RequestParam(value = "roleName") String roleName, @RequestParam(value = "roleId") String roleId)
	{
		req.setAttribute("roleName", roleName);
		req.setAttribute("roleId", roleId);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/back/role_update");
		return mav;
	}

	// 更新角色名字
	@RequestMapping(value = "/updateRoleName.action")
	@ResponseBody
	public String updateRoleName(@RequestBody Role role)
	{
		if (adminService.updateRoleName(role))
		{
			return "success";
		} else
		{
			return "failed";
		}
	}
	// 查询角色
	/*
	 * @RequestMapping(value = "/queryRole.action") public ModelAndView
	 * queryRole(HttpServletRequest req) { ModelAndView mav = new ModelAndView();
	 * List<Role> roleList = adminService.queryRole(); req.setAttribute("roleList",
	 * roleList); mav.setViewName("/back/role_manage"); return mav; }
	 */
}
