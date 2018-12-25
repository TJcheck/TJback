package great.mapper;

import great.bean.Admin;

public interface AdminMapper
{
	// 用户登录
	public Admin adminLogin(Admin admin);
	 
	// 重置密码
	public int updateAdminPwd(Admin admin);
}
