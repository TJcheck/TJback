package great.bean;

//项目细项中间表
public class ProjectItem {
	private int projectId;// 项目Id
	private int itemId;// 细项
	private String itemName;// 细项名
	private String itemUnit;// 细项单位
	private float itemLess;// 最小值
	private float itemHigh;// 最高值

	public ProjectItem() {
		super();
	}

	public ProjectItem(int projectId, int itemId) {
		super();
		this.projectId = projectId;
		this.itemId = itemId;
	}

	public ProjectItem(int projectId, int itemId, String itemName, String itemUnit, float itemLess, float itemHigh) {
		super();
		this.projectId = projectId;
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemUnit = itemUnit;
		this.itemLess = itemLess;
		this.itemHigh = itemHigh;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemUnit() {
		return itemUnit;
	}

	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}

	public float getItemLess() {
		return itemLess;
	}

	public void setItemLess(float itemLess) {
		this.itemLess = itemLess;
	}

	public float getItemHigh() {
		return itemHigh;
	}

	public void setItemHigh(float itemHigh) {
		this.itemHigh = itemHigh;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	@Override
	public String toString() {
		return "ProjectItem [projectId=" + projectId + ", itemId=" + itemId + ", itemName=" + itemName + ", itemUnit="
				+ itemUnit + ", itemLess=" + itemLess + ", itemHigh=" + itemHigh + "]";
	}
}
