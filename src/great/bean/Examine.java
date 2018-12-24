package great.bean;

public class Examine {
	private int examineId;//体检号ID
	private int companyId;//公司ID
	private String exName;//姓名
	private String exSex;//性别
	private String exAge;//年龄
	private String exPhone;//手机号码
	private int comboId;
	
	public Examine() {
		// TODO Auto-generated constructor stub
	}

	public Examine(int examineId, int companyId, String exName, String exSex, String exAge, String exPhone,
			int comboId) {
		super();
		this.examineId = examineId;
		this.companyId = companyId;
		this.exName = exName;
		this.exSex = exSex;
		this.exAge = exAge;
		this.exPhone = exPhone;
		this.comboId = comboId;
	}



	public int getExamineId() {
		return examineId;
	}
	public void setExamineId(int examineId) {
		this.examineId = examineId;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getExName() {
		return exName;
	}
	public void setExName(String exName) {
		this.exName = exName;
	}
	public String getExSex() {
		return exSex;
	}
	public void setExSex(String exSex) {
		this.exSex = exSex;
	}
	public String getExAge() {
		return exAge;
	}
	public void setExAge(String exAge) {
		this.exAge = exAge;
	}
	public String getExPhone() {
		return exPhone;
	}
	public void setExPhone(String exPhone) {
		this.exPhone = exPhone;
	}

	public int getComboId() {
		return comboId;
	}

	public void setComboId(int comboId) {
		this.comboId = comboId;
	}

	@Override
	public String toString() {
		return "Examine [examineId=" + examineId + ", companyId=" + companyId + ", exName=" + exName + ", exSex="
				+ exSex + ", exAge=" + exAge + ", exPhone=" + exPhone + ", comboId=" + comboId + "]";
	}
	
	
	
}
