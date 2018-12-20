package great.bean;

import java.util.Arrays;
//套餐类
public class Combo {
	private int comboId;
	private String comboName;
	private int companyId;//公司Id
	private int comboCost;//套餐价格
	private String companyName;//公司名
	private int[] projects;// 项目内细项串Id
	
	
	public Combo() {
		super();
	}
	public Combo(int comboId, String comboName, int companyId, int comboCost,String companyName, int[] projects) {
		super();
		this.comboId = comboId;
		this.comboName = comboName;
		this.companyId = companyId;
		this.comboCost = comboCost;
		this.companyName = companyName;
		this.projects = projects;
	}
	
	@Override
	public String toString() {
		return "Combo [comboId=" + comboId + ", comboName=" + comboName + ", companyId=" + companyId + ", companyName="
				+ companyName + ", projects=" + Arrays.toString(projects) + "]";
	}
	
	public int getComboCost() {
		return comboCost;
	}
	public void setComboCost(int comboCost) {
		this.comboCost = comboCost;
	}
	
	public int getComboId() {
		return comboId;
	}
	public void setComboId(int comboId) {
		this.comboId = comboId;
	}
	public String getComboName() {
		return comboName;
	}
	public void setComboName(String comboName) {
		this.comboName = comboName;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int[] getProjects() {
		return projects;
	}
	public void setProjects(int[] projects) {
		this.projects = projects;
	}

}
