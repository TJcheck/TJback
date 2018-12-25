package great.bean;

public class Company
{
	// 公司Id
	private int companyId;
	// 公司名字
	private String companyName;
	// 公司密码
	private String companyPwd;
	// 公司余额
	private int companyBalance;
	// 套餐Id
	private int comboId;
	// 套餐名字
	private String comboName;
	// 套餐价格
	private int comboCost;
	// 公司消费总价格
	private int allCost;
	// 公司已体检人数
	private int peopleNum;

	public int getPeopleNum()
	{
		return peopleNum;
	}

	public void setPeopleNum(int peopleNum)
	{
		this.peopleNum = peopleNum;
	}

	public int getCompanyId()
	{
		return companyId;
	}

	public void setCompanyId(int companyId)
	{
		this.companyId = companyId;
	}

	public String getCompanyName()
	{
		return companyName;
	}

	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}

	public String getCompanyPwd()
	{
		return companyPwd;
	}

	public void setCompanyPwd(String companyPwd)
	{
		this.companyPwd = companyPwd;
	}

	public int getCompanyBalance()
	{
		return companyBalance;
	}

	public void setCompanyBalance(int companyBalance)
	{
		this.companyBalance = companyBalance;
	}

	public int getComboId()
	{
		return comboId;
	}

	public void setComboId(int comboId)
	{
		this.comboId = comboId;
	}

	public int getComboCost()
	{
		return comboCost;
	}

	public void setComboCost(int comboCost)
	{
		this.comboCost = comboCost;
	}

	public int getAllCost()
	{
		return allCost;
	}

	public void setAllCost(int allCost)
	{
		this.allCost = allCost;
	}

	public String getComboName()
	{
		return comboName;
	}

	public void setComboName(String comboName)
	{
		this.comboName = comboName;
	}

	public Company()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public Company(int companyId, String companyName, String companyPwd, int companyBalance, int comboId, String comboName, int comboCost, int allCost, int peopleNum)
	{
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyPwd = companyPwd;
		this.companyBalance = companyBalance;
		this.comboId = comboId;
		this.comboName = comboName;
		this.comboCost = comboCost;
		this.allCost = allCost;
		this.peopleNum = peopleNum;
	}

}
