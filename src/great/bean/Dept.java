package great.bean;

import java.io.Serializable;

//科室表
public class Dept implements Serializable{
	private int deptId;
	private String deptName;
	
	public Dept() {
		super();
	}

	public Dept(int deptId, String deptName) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
	}
	
	
	
	@Override
	public String toString() {
		return "Dept [deptId=" + deptId + ", deptName=" + deptName + "]";
	}

	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	

}
