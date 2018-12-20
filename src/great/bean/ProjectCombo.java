package great.bean;
//项目套餐中间表
public class ProjectCombo {
	private int comboId;
	private int projectId;
	
	public ProjectCombo() {
		super();
	}
	public ProjectCombo(int comboId, int projectId) {
		super();
		this.comboId = comboId;
		this.projectId = projectId;
	}
	public int getComboId() {
		return comboId;
	}
	public void setComboId(int comboId) {
		this.comboId = comboId;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

}
