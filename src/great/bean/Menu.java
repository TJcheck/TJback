package great.bean;

public class Menu
{
	private int menuId;
	private String menuName;
	private String menuUrl;
	private int parentId;

	public Menu()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public Menu(int menuId, String menuName, String menuUrl, int parentId)
	{
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuUrl = menuUrl;
		this.parentId = parentId;
	}

	public int getMenuId()
	{
		return menuId;
	}

	public void setMenuId(int menuId)
	{
		this.menuId = menuId;
	}

	public String getMenuName()
	{
		return menuName;
	}

	public void setMenuName(String menuName)
	{
		this.menuName = menuName;
	}

	public String getMenuUrl()
	{
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl)
	{
		this.menuUrl = menuUrl;
	}

	public int getParentId()
	{
		return parentId;
	}

	public void setParentId(int parentId)
	{
		this.parentId = parentId;
	}

	@Override
	public String toString()
	{
		return "Menu [menuId=" + menuId + ", menuName=" + menuName + ", menuUrl=" + menuUrl + ", parentId=" + parentId + "]";
	}

}
