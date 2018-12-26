package great.bean;

//项目套餐中间表
public class ProjectCombo{
	private int comboId;// 套餐ID
	private int projectId;// 项目ID
	private String projectName;// 项目名
	private int deptId;// 科室ID
	private String deptName;// 科室名
	private int projectPrice;// 项目单价
	private int projectType;//项目类型
	private String parameterName;//类型名
	public ProjectCombo() {
		// TODO Auto-generated constructor stub
	}
	
	public ProjectCombo(int comboId, int projectId) {
		super();
		this.comboId = comboId;
		this.projectId = projectId;
	}

	public ProjectCombo(int comboId, int projectId, String projectName, int deptId, String deptName) {
		super();
		this.comboId = comboId;
		this.projectId = projectId;
		this.projectName = projectName;
		this.deptId = deptId;
		this.deptName = deptName;
	}

	public ProjectCombo(int comboId, int projectId, String projectName, int deptId, String deptName,
			int projectPrice) {
		super();
		this.comboId = comboId;
		this.projectId = projectId;
		this.projectName = projectName;
		this.deptId = deptId;
		this.deptName = deptName;
		this.projectPrice = projectPrice;
	}

	public ProjectCombo(int comboId, int projectId, String projectName, int deptId, String deptName, int projectPrice,
			int projectType, String parameterName) {
		super();
		this.comboId = comboId;
		this.projectId = projectId;
		this.projectName = projectName;
		this.deptId = deptId;
		this.deptName = deptName;
		this.projectPrice = projectPrice;
		this.projectType = projectType;
		this.parameterName = parameterName;
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

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getProjectPrice() {
		return projectPrice;
	}

	public void setProjectPrice(int projectPrice) {
		this.projectPrice = projectPrice;
	}

	public int getProjectType() {
		return projectType;
	}

	public void setProjectType(int projectType) {
		this.projectType = projectType;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	@Override
	public String toString() {
		return "ProjectCombo [comboId=" + comboId + ", projectId=" + projectId + ", projectName=" + projectName
				+ ", deptId=" + deptId + ", deptName=" + deptName + ", projectPrice=" + projectPrice + ", projectType="
				+ projectType + ", parameterName=" + parameterName + "]";
	}
	
}
