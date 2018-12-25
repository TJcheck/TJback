package great.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import great.bean.Admin;
import great.mapper.AdminMapper;

@Service
public class AdminService
{
	@Autowired
	AdminMapper adminMapper;

	// 用户登录
	public Admin adminLogin(Admin admin)
	{
		return adminMapper.adminLogin(admin);
	}

	// 重置密码
	public boolean updateAdminPwd(Admin admin)
	{
		return adminMapper.updateAdminPwd(admin) > 0;
	}
}
