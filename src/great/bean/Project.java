package great.bean;

import java.util.Arrays;

//项目
public class Project {
	private int projectId;
	private String projectName;
	private int deptId;
	private String deptName;
	private int projectPrice;
	private int[] items;// 项目内细项串Id
	private boolean checked;//勾选状态

	public Project() {
		super();
	}

	public Project(int projectId, String projectName, int deptId, String deptName, int projectPrice) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.deptId = deptId;
		this.deptName = deptName;
		this.projectPrice = projectPrice;
	}

	public Project(int projectId, String projectName, int deptId, String deptName, int projectPrice, int[] items,
			boolean checked) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.deptId = deptId;
		this.deptName = deptName;
		this.projectPrice = projectPrice;
		this.items = items;
		this.checked = checked;
	}

	public Project(int projectId, String projectName, int deptId, String deptName, int projectPrice, int[] items) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.deptId = deptId;
		this.deptName = deptName;
		this.projectPrice = projectPrice;
		this.items = items;
	}

	
	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", deptId=" + deptId + ", deptName="
				+ deptName + ", projectPrice=" + projectPrice + ", items=" + Arrays.toString(items) + "]";
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	public int[] getItems() {
		return items;
	}


	public void setItems(int[] items) {
		this.items = items;
	}


	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public int getProjectPrice() {
		return projectPrice;
	}

	public void setProjectPrice(int projectPrice) {
		this.projectPrice = projectPrice;
	}
}
