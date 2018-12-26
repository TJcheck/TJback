package great.bean;

import org.springframework.stereotype.Component;

@Component
public class ItemResult {
	private int examineId;//体检号
	private int projectId;//项目ID
	private String projectName;//项目名
	private int itemId;//细项ID
	private String itemName;//细项名
	private float itemResult;//细项值
	private String itemUnit;//单位
	private int itemLess;//下限值
	private int itemHigh;//上限值
	private String imgAddress;//图片地址
	public ItemResult() {
		// TODO Auto-generated constructor stub
	}
	public ItemResult(int examineId, int projectId, int itemId) {
		super();
		this.examineId = examineId;
		this.projectId = projectId;
		this.itemId = itemId;
	}
	public ItemResult(int examineId, int projectId, int itemId, String itemName, float itemResult, String itemUnit,
			int itemLess, int itemHigh, String imgAddress) {
		super();
		this.examineId = examineId;
		this.projectId = projectId;
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemResult = itemResult;
		this.itemUnit = itemUnit;
		this.itemLess = itemLess;
		this.itemHigh = itemHigh;
		this.imgAddress = imgAddress;
	}
	
	public ItemResult(int examineId, int projectId, String projectName, int itemId, String itemName, float itemResult,
			String itemUnit, int itemLess, int itemHigh, String imgAddress) {
		super();
		this.examineId = examineId;
		this.projectId = projectId;
		this.projectName = projectName;
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemResult = itemResult;
		this.itemUnit = itemUnit;
		this.itemLess = itemLess;
		this.itemHigh = itemHigh;
		this.imgAddress = imgAddress;
	}
	public int getExamineId() {
		return examineId;
	}
	public void setExamineId(int examineId) {
		this.examineId = examineId;
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
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public float getItemResult() {
		return itemResult;
	}
	public void setItemResult(float itemResult) {
		this.itemResult = itemResult;
	}
	public String getItemUnit() {
		return itemUnit;
	}
	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}
	public int getItemLess() {
		return itemLess;
	}
	public void setItemLess(int itemLess) {
		this.itemLess = itemLess;
	}
	public int getItemHigh() {
		return itemHigh;
	}
	public void setItemHigh(int itemHigh) {
		this.itemHigh = itemHigh;
	}
	public String getImgAddress() {
		return imgAddress;
	}
	public void setImgAddress(String imgAddress) {
		this.imgAddress = imgAddress;
	}
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	@Override
	public String toString() {
		return "ItemResult [examineId=" + examineId + ", projectId=" + projectId + ", projectName=" + projectName
				+ ", itemId=" + itemId + ", itemName=" + itemName + ", itemResult=" + itemResult + ", itemUnit="
				+ itemUnit + ", itemLess=" + itemLess + ", itemHigh=" + itemHigh + ", imgAddress=" + imgAddress + "]";
	}
	
}
