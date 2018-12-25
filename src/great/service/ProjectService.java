package great.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import great.bean.Project;
import great.bean.ProjectItem;
import great.mapper.ProjectMapper;
@Service
//@Transactional//事务注解，需要在spring 配置文件中开启事务注解驱动
public class ProjectService {
	
	// 注入项目映射器
	@Resource
    private ProjectMapper projectMapper;
	
	// 添加项目
	public boolean addProject(Project project) {
		int[] itemIds = project.getItems();
		int projectId = projectMapper.createId();
		project.setProjectId(projectId);
		//添加到项目中间表
		Map<String, Object> map = new HashMap<>();
        map.put("projectId", projectId);
        map.put("itemIds", itemIds);
        int ret1 =projectMapper.addProject(project);
        int ret2 =projectMapper.addItems(map);
        boolean flag = ret1>0&&ret2>0;
		return flag;
	}

	// 查找项目
	public List<Project> queryProject(Project project) {
		return projectMapper.queryProject(project);
	}

	// 更新项目
	public boolean updateProject(Project project) {
		int[] itemIds = project.getItems();
		int projectId = project.getProjectId();
		//删除
		int ret1 = projectMapper.delProItems(projectId);
		//插入中间表
		Map<String, Object> map = new HashMap<>();
		map.put("projectId", projectId);
		map.put("itemIds", itemIds);
		int ret2 = projectMapper.addItems(map);
		int ret3 = projectMapper.updateProject(project);
		boolean flag = ret1>0&&ret2>0&&ret3>0;
		return flag;
	}

	// 删除项目
	public boolean delProject(int projectId) {
		int ret =projectMapper.delProject(projectId);
		int ret1 =projectMapper.delProItems(projectId);
		boolean flag = ret>0&& ret1>0;
		return flag;
	}
	
	//查询项目细项
	public List<ProjectItem> queryItems(ProjectItem projectItem){
		return projectMapper.queryItems(projectItem);
	}

}
