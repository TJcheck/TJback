package great.bean;

public class Admin
{
	public int adminId;
	public String adminName;
	public String adminPwd;
	public String deptId;
	public String adminPhone;
	public int roleId;

	public Admin()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(int adminId, String adminName, String adminPwd, String deptId, String adminPhone, int roleId)
	{
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminPwd = adminPwd;
		this.deptId = deptId;
		this.adminPhone = adminPhone;
		this.roleId = roleId;
	}

	public int getAdminId()
	{
		return adminId;
	}

	public void setAdminId(int adminId)
	{
		this.adminId = adminId;
	}

	public String getAdminName()
	{
		return adminName;
	}

	public void setAdminName(String adminName)
	{
		this.adminName = adminName;
	}

	public String getAdminPwd()
	{
		return adminPwd;
	}

	public void setAdminPwd(String adminPwd)
	{
		this.adminPwd = adminPwd;
	}

	public String getDeptId()
	{
		return deptId;
	}

	public void setDeptId(String deptId)
	{
		this.deptId = deptId;
	}

	public String getAdminPhone()
	{
		return adminPhone;
	}

	public void setAdminPhone(String adminPhone)
	{
		this.adminPhone = adminPhone;
	}

	public int getRoleId()
	{
		return roleId;
	}

	public void setRoleId(int roleId)
	{
		this.roleId = roleId;
	}

}
