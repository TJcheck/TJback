package great.bean;
//
public class Examine {
	private int examineId;//体检号ID
	private int companyId;//公司ID
	private String companyName;//公司名
	private String exName;//姓名
	private String exSex;//性别
	private String exAge;//年龄
	private String exPhone;//手机号码
	private int comboId;//套餐ID
	private String comboName;//套餐名
	private String exDate;//体检日期
	private String cDate;//打印日期
	private String conclusionl;//医生总觉
	private String exSuggest;//建议
	private String exGuide;//生活保健指导
	private int adminId;//医生ID
	private String conclusionDate;//总结ID
	private int exStatus;//体检完成状态
	private String parameterName;//完成状态名
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

	public Examine(int examineId, int companyId, String companyName, String exName, String exSex, String exAge,
			String exPhone, int comboId, String comboName, String exDate, String cDate, String conclusionl,
			String exSuggest, String exGuide, int adminId, String conclusionDate, int exStatus, String parameterName) {
		super();
		this.examineId = examineId;
		this.companyId = companyId;
		this.companyName = companyName;
		this.exName = exName;
		this.exSex = exSex;
		this.exAge = exAge;
		this.exPhone = exPhone;
		this.comboId = comboId;
		this.comboName = comboName;
		this.exDate = exDate;
		this.cDate = cDate;
		this.conclusionl = conclusionl;
		this.exSuggest = exSuggest;
		this.exGuide = exGuide;
		this.adminId = adminId;
		this.conclusionDate = conclusionDate;
		this.exStatus = exStatus;
		this.parameterName = parameterName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getComboName() {
		return comboName;
	}

	public void setComboName(String comboName) {
		this.comboName = comboName;
	}

	public String getExDate() {
		return exDate;
	}

	public void setExDate(String exDate) {
		this.exDate = exDate;
	}

	public String getcDate() {
		return cDate;
	}

	public void setcDate(String cDate) {
		this.cDate = cDate;
	}

	public String getConclusionl() {
		return conclusionl;
	}

	public void setConclusionl(String conclusionl) {
		this.conclusionl = conclusionl;
	}

	public String getExSuggest() {
		return exSuggest;
	}

	public void setExSuggest(String exSuggest) {
		this.exSuggest = exSuggest;
	}

	public String getExGuide() {
		return exGuide;
	}

	public void setExGuide(String exGuide) {
		this.exGuide = exGuide;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getConclusionDate() {
		return conclusionDate;
	}

	public void setConclusionDate(String conclusionDate) {
		this.conclusionDate = conclusionDate;
	}

	public int getExStatus() {
		return exStatus;
	}

	public void setExStatus(int exStatus) {
		this.exStatus = exStatus;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
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
		return "Examine [examineId=" + examineId + ", companyId=" + companyId + ", companyName=" + companyName
				+ ", exName=" + exName + ", exSex=" + exSex + ", exAge=" + exAge + ", exPhone=" + exPhone + ", comboId="
				+ comboId + ", comboName=" + comboName + ", exDate=" + exDate + ", cDate=" + cDate + ", conclusionl="
				+ conclusionl + ", exSuggest=" + exSuggest + ", exGuide=" + exGuide + ", adminId=" + adminId
				+ ", conclusionDate=" + conclusionDate + ", exStatus=" + exStatus + ", parameterName=" + parameterName
				+ "]";
	}

}
