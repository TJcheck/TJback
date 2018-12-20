package great.mapper;

import java.util.List;

import great.bean.Dept;

public interface DeptMapper {
	// 添加科室
	public int addDept(String deptName);

	// 查找所有科室
	public List<Dept> queryDept(Dept dept);

	// 删除科室
	public int delDept(int deptId);

	// 修改科室名称
	public int updateDeptName(Dept dept);

}
