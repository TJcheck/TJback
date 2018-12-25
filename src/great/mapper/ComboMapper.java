package great.mapper;

import java.util.List;
import java.util.Map;

import great.bean.Combo;
import great.bean.ProjectCombo;
//套餐
public interface ComboMapper {
	// 创建项目Id
	public int createId();

	// 批量添加项目
	public int addProjects(Map<String, Object> map);

	// 添加套餐
	public int addCombo(Combo combo);

	// 查找所有套餐
	public List<Combo> queryCombo(Combo combo);

	// 删除套餐
	public int delCombo(int comboId);

	// 修改套餐
	public int updateCombo(Combo combo);
	
	//查询套餐项目
	public List<ProjectCombo> queryProjects(ProjectCombo projectCombo);
	
	//删除套餐项目中间表
	public int delProCombo(int comboId);
	
	//查询套餐中间表
	public List<ProjectCombo> queryCheckPro(int comboId);
}
