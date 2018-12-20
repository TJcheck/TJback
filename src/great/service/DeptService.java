package great.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import great.bean.Dept;
import great.mapper.DeptMapper;

@Service
public class DeptService {
	// 注入科室映射器
	@Resource
	private DeptMapper deptMapper;
	
	// 添加科室
	public boolean addDept(String deptName) {
		return deptMapper.addDept(deptName) > 0;
	}

	// 查找科室
	public List<Dept> queryDept(Dept dept) {
		return deptMapper.queryDept(dept);
	}

	// 更新科室
	public boolean updateDeptName(Dept dept) {
		return deptMapper.updateDeptName(dept) > 0;
	}

	// 删除科室
	public boolean delDept(Integer deptId) {
		return deptMapper.delDept(deptId) > 0;
	}
	
}
