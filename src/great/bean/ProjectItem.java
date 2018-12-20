package great.bean;
//项目细项中间表
public class ProjectItem {
	private int projectId;//项目Id
    private int itemId;//细项

    public ProjectItem() {
		super();
	}
    
	public ProjectItem(int projectId, int itemId) {
		super();
		this.projectId = projectId;
		this.itemId = itemId;
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
}
