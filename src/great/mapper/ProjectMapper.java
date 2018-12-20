package great.mapper;

import java.util.List;
import java.util.Map;

import great.bean.Project;
import great.bean.ProjectItem;

//项目
public interface ProjectMapper {
	// 创建项目Id
	public int createId();

	// 批量添加细项
	public int addItems(Map<String, Object> map);

	// 添加项目
	public int addProject(Project project);

	// 查找所有项目
	public List<Project> queryProject(Project project);

	// 删除项目
	public int delProject(int projectId);

	// 修改项目
	public int updateProject(Project project);
	
	//查询项目细项
	public List<ProjectItem> queryItems(ProjectItem projectItem);
	
	//删除项目细项
	public int delProItems(int projectId);
}
