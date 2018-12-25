package great.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import great.bean.Combo;
import great.bean.ProjectCombo;
import great.mapper.ComboMapper;

@Service
@Transactional//事务注解，需要在spring 配置文件中开启事务注解驱动
public class ComboService {
	// 注入项目映射器
	@Resource
	private ComboMapper comboMapper;
	
	//批量删除套餐
	public boolean delCombos(int[] comboIds) {
		//删除套餐项目中间表
		int ret = comboMapper.delProCombos(comboIds);
		//删除套餐
		int ret1 = comboMapper.delCombos(comboIds);
		boolean flag = ret>0&&ret1>0;
		return flag;
	}
	// 添加套餐
	public boolean addCombo(Combo combo) {
		int[] projectIds = combo.getProjects();
		int comboId = comboMapper.createId();
		combo.setComboId(comboId);
		// 添加到项目中间表
		Map<String, Object> map = new HashMap<>();
		map.put("comboId", comboId);
		map.put("projectIds", projectIds);
		int ret1 = comboMapper.addCombo(combo);
		int ret2 = comboMapper.addProjects(map);
		boolean flag = ret1 > 0 && ret2 > 0;
		return flag;
	}

	// 查找套餐
	public List<Combo> queryCombo(Combo combo) {
		return comboMapper.queryCombo(combo);
	}

	// 更新套餐
	public boolean updateCombo(Combo combo) {
		int[] projectIds = combo.getProjects();
		int comboId = combo.getComboId();
		// 删除
		int ret1 = comboMapper.delProCombo(comboId);
		// 插入中间表
		Map<String, Object> map = new HashMap<>();
		map.put("comboId", comboId);
		map.put("projectIds", projectIds);
		int ret2 = comboMapper.addProjects(map);
		int ret3 = comboMapper.updateCombo(combo);
		boolean flag = ret1 > 0 && ret2 > 0 && ret3 > 0;
		return flag;
	}

	// 删除项目
	public boolean delCombo(int comboId) {
		//删除中间表
		int ret = comboMapper.delProCombo(comboId);
		int ret1 = comboMapper.delCombo(comboId);
	    boolean flag = ret>0&&ret1>0;
		return flag;
	}

	// 查询项目细项
	public List<ProjectCombo> queryProjects(ProjectCombo projectCombo) {
		return comboMapper.queryProjects(projectCombo);
	}

	// 通过套餐Id查询项目
	public List<ProjectCombo> queryCheckPro(int comboId) {
		return comboMapper.queryCheckPro(comboId);
	}

}
