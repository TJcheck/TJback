package great.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import great.bean.Company;
import great.mapper.CompanyMapper;

@Service
public class CompanyService
{
	@Autowired
	CompanyMapper companyMapper;

	// 查询团体信息
	public List<Company> queryOrderInfo(Company company)
	{
		return companyMapper.queryOrderInfo(company);
	}
}
