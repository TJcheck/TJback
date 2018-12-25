package great.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import great.bean.Combo;
import great.bean.ProjectCombo;
import great.mapper.ComboMapper;
@Service
//@Transactional//事务注解，需要在spring 配置文件中开启事务注解驱动
public class ComboService {
	// 注入项目映射器
	@Resource
    private ComboMapper comboMapper;
	
	// 添加套餐
	public boolean addCombo(Combo combo) {
		int[] projectIds = combo.getProjects();
		int comboId = comboMapper.createId();
		combo.setComboId(comboId);
		//添加到项目中间表
		Map<String, Object> map = new HashMap<>();
        map.put("comboId", comboId);
        map.put("projectIds", projectIds);
        int ret1 =comboMapper.addCombo(combo);
        int ret2 =comboMapper.addProjects(map);
        boolean flag = ret1>0&&ret2>0;
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
		//删除
		int ret1 = comboMapper.delProCombo(comboId);
		//插入中间表
		Map<String, Object> map = new HashMap<>();
		map.put("comboId", comboId);
		map.put("projectIds", projectIds);
		int ret2 = comboMapper.addProjects(map);
		int ret3 = comboMapper.updateCombo(combo);
		boolean flag = ret1>0&&ret2>0&&ret3>0;
		return flag;
	}

	// 删除项目
	public boolean delCombo(int comboId) {
		return comboMapper.delCombo(comboId)> 0;
	}
	
	//查询项目细项
	public List<ProjectCombo> queryProjects(ProjectCombo projectCombo){
		return comboMapper.queryProjects(projectCombo);
	}
	
	public List<ProjectCombo> queryCheckPro(int comboId){
		return comboMapper.queryCheckPro(comboId);
	}
}
