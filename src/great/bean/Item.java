package great.bean;

public class Item {
	private int itemId;
	private String itemName;
	private String itemUnit;// 细项单位
	private float itemLess;// 最小值
	private float itemHigh;// 最高值
	private boolean checked;//勾选状态

	public Item() {
		super();
	}

	public Item(int itemId, String itemName, String itemUnit, float itemLess, float itemHigh, boolean checked) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemUnit = itemUnit;
		this.itemLess = itemLess;
		this.itemHigh = itemHigh;
		this.checked = checked;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", itemUnit=" + itemUnit + ", itemLess=" + itemLess
				+ ", itemHigh=" + itemHigh + "]";
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
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

}
