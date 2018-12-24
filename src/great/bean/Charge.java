package great.bean;

import org.springframework.stereotype.Component;

//记账表
@Component
public class Charge {
	private int chargeId;//记账ID
	private int companyId;//公司ID
	private String companyName;//公司名
	private int comboId;//套餐ID
	private String comboName;//套餐名
	private int chargeNum;//团检人数
	private int chargePrice;//团检金额
	private int reservationStatus;//预约状态
	private String reservationParameter;//预约状态名
	private String reservationTime;//预约时间
	private String examineTime;//体检时间
	private int chargeStatus;//结账状态
	private String chargeParameter;//结账状态名
	
	public Charge() {
		// TODO Auto-generated constructor stub
	}

	public Charge(int chargeId, int companyId, String companyName, int comboId, String comboName, int chargeNum,
			int chargePrice, int reservationStatus, String reservationParameter, String reservationTime,
			String examineTime, int chargeStatus, String chargeParameter) {
		super();
		this.chargeId = chargeId;
		this.companyId = companyId;
		this.companyName = companyName;
		this.comboId = comboId;
		this.comboName = comboName;
		this.chargeNum = chargeNum;
		this.chargePrice = chargePrice;
		this.reservationStatus = reservationStatus;
		this.reservationParameter = reservationParameter;
		this.reservationTime = reservationTime;
		this.examineTime = examineTime;
		this.chargeStatus = chargeStatus;
		this.chargeParameter = chargeParameter;
	}


	public int getChargeId() {
		return chargeId;
	}
	public void setChargeId(int chargeId) {
		this.chargeId = chargeId;
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
	public int getChargeNum() {
		return chargeNum;
	}
	public void setChargeNum(int chargeNum) {
		this.chargeNum = chargeNum;
	}
	public int getReservationStatus() {
		return reservationStatus;
	}
	public void setReservationStatus(int reservationStatus) {
		this.reservationStatus = reservationStatus;
	}
	public String getReservationParameter() {
		return reservationParameter;
	}
	public void setReservationParameter(String reservationParameter) {
		this.reservationParameter = reservationParameter;
	}
	public String getReservationTime() {
		return reservationTime;
	}
	public void setReservationTime(String reservationTime) {
		this.reservationTime = reservationTime;
	}
	public String getExamineTime() {
		return examineTime;
	}
	public void setExamineTime(String examineTime) {
		this.examineTime = examineTime;
	}
	public int getChargeStatus() {
		return chargeStatus;
	}
	public void setChargeStatus(int chargeStatus) {
		this.chargeStatus = chargeStatus;
	}
	public String getChargeParameter() {
		return chargeParameter;
	}
	public void setChargeParameter(String chargeParameter) {
		this.chargeParameter = chargeParameter;
	}

	public int getChargePrice() {
		return chargePrice;
	}

	public void setChargePrice(int chargePrice) {
		this.chargePrice = chargePrice;
	}

	@Override
	public String toString() {
		return "Charge [chargeId=" + chargeId + ", companyId=" + companyId + ", companyName=" + companyName
				+ ", comboId=" + comboId + ", comboName=" + comboName + ", chargeNum=" + chargeNum + ", chargePrice="
				+ chargePrice + ", reservationStatus=" + reservationStatus + ", reservationParameter="
				+ reservationParameter + ", reservationTime=" + reservationTime + ", examineTime=" + examineTime
				+ ", chargeStatus=" + chargeStatus + ", chargeParameter=" + chargeParameter + "]";
	}

	
}
