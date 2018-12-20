package great.bean;

import java.io.Serializable;
import java.util.Map;

public class PageInfo implements Serializable
{
	/* 当前页数，总页数 ，总记录数，数据 */
	private Integer currentPage;
	private Integer totalPage;
	private Integer totalNum;
	private Map<String, Object> datasets;

	public PageInfo()
	{
		super();
	}

	public PageInfo(Integer currentPage, Integer totalPage, Integer totalNum, Map<String, Object> datasets)
	{
		super();
		this.currentPage = currentPage;
		this.totalPage = totalPage;
		this.totalNum = totalNum;
		this.datasets = datasets;
	}

	public Integer getcurrentPage()
	{
		return currentPage;
	}

	public void setcurrentPage(Integer currentPage)
	{
		this.currentPage = currentPage;
	}

	public Integer getTotalPage()
	{
		return totalPage;
	}

	public void setTotalPage(Integer totalPage)
	{
		this.totalPage = totalPage;
	}

	public Integer getTotalNum()
	{
		return totalNum;
	}

	public void setTotalNum(Integer totalNum)
	{
		this.totalNum = totalNum;
	}

	public Map<String, Object> getdatasets()
	{
		return datasets;
	}

	public void setdatasets(Map<String, Object> datasets)
	{
		this.datasets = datasets;
	}

}
