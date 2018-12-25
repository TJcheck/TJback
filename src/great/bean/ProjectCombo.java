package great.bean;

//项目套餐中间表
public class ProjectCombo {
	private int comboId;// 套餐ID
	private int projectId;// 项目ID
	private String projectName;//项目名
	private int deptId;//科室ID
	private String deptName;//科室名

	public ProjectCombo() {
		super();
	}

	public ProjectCombo(int comboId, int projectId) {
		super();
		this.comboId = comboId;
		this.projectId = projectId;
	}

	public ProjectCombo(int comboId, int projectId, String projectName) {
		super();
		this.comboId = comboId;
		this.projectId = projectId;
		this.projectName = projectName;
	}
	
	

	public ProjectCombo(int comboId, int projectId, String projectName, int deptId, String deptName) {
		super();
		this.comboId = comboId;
		this.projectId = projectId;
		this.projectName = projectName;
		this.deptId = deptId;
		this.deptName = deptName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	public String toString() {
		return "ProjectCombo [comboId=" + comboId + ", projectId=" + projectId + ", projectName=" + projectName
				+ ", deptId=" + deptId + ", deptName=" + deptName + "]";
	}


}
