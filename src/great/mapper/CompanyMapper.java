package great.mapper;

import java.util.List;

import great.bean.Company;

public interface CompanyMapper
{
	// 查询团体信息
	public List<Company> queryOrderInfo(Company company);
}
