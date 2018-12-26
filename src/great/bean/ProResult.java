package great.bean;

public class ProResult {
	private int proProResultId;//小结ID
	private int examineId;//体检ID
	private int projectId;//项目ID
	private String projectName;//项目名
	private int proResultStatus;//接收状态
	private String parameterName;//状态名
	private int adminId;//医生ID
	private String summary;//小结
	
	public ProResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ProResult(int examineId, int projectId) {
		super();
		this.examineId = examineId;
		this.projectId = projectId;
	}

	public ProResult(int proProResultId, int examineId, int projectId, String projectName, int proResultStatus,
			String parameterName, int adminId, String summary) {
		super();
		this.proProResultId = proProResultId;
		this.examineId = examineId;
		this.projectId = projectId;
		this.projectName = projectName;
		this.proResultStatus = proResultStatus;
		this.parameterName = parameterName;
		this.adminId = adminId;
		this.summary = summary;
	}


	public int getProProResultId() {
		return proProResultId;
	}

	public void setProProResultId(int proProResultId) {
		this.proProResultId = proProResultId;
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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public int getProResultStatus() {
		return proResultStatus;
	}

	public void setProResultStatus(int proResultStatus) {
		this.proResultStatus = proResultStatus;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Override
	public String toString() {
		return "ProResult [proProResultId=" + proProResultId + ", examineId=" + examineId + ", projectId=" + projectId
				+ ", projectName=" + projectName + ", ProResultStatus=" + proResultStatus + ", parameterName="
				+ parameterName + ", adminId=" + adminId + ", Summary=" + summary + "]";
	}

	
	
}
