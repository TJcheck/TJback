package great.bean;

import java.io.Serializable;

public class ztreeNode implements Serializable
{
	private String menuName;
	private int menuId;
	private int parentId;
	private boolean selected;

	public ztreeNode()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public ztreeNode(String menuName, int menuId, int parentId, boolean selected)
	{
		super();
		this.menuName = menuName;
		this.menuId = menuId;
		this.parentId = parentId;
		this.selected = selected;
	}

	public boolean isSelected()
	{
		return selected;
	}

	public void setSelected(boolean selected)
	{
		this.selected = selected;
	}

	public String getMenuName()
	{
		return menuName;
	}

	public void setMenuName(String menuName)
	{
		this.menuName = menuName;
	}

	public int getMenuId()
	{
		return menuId;
	}

	public void setMenuId(int menuId)
	{
		this.menuId = menuId;
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
		return "ztreeNode [menuName=" + menuName + ", menuId=" + menuId + ", parentId=" + parentId + ", selected=" + selected + "]";
	}

}
