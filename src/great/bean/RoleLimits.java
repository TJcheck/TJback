package great.bean;

public class RoleLimits
{
	private int roleId;
	private int menuId;

	public RoleLimits()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public RoleLimits(int roleId, int menuId)
	{
		super();
		this.roleId = roleId;
		this.menuId = menuId;
	}

	public int getRoleId()
	{
		return roleId;
	}

	public void setRoleId(int roleId)
	{
		this.roleId = roleId;
	}

	public int getMenuId()
	{
		return menuId;
	}

	public void setMenuId(int menuId)
	{
		this.menuId = menuId;
	}

}
