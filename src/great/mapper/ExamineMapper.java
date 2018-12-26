package great.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import great.bean.Examine;

@Repository
public interface ExamineMapper {
	//添加个人体检表
	public int addExamine(Examine examine);
	//查询个人体检
	public Examine queryExamine(int examineId);

	public List<Integer> queryExamineId(int companyId);

	public int deleteExamine(int companyId);

	public List<Examine> queryExamineInfo(int companyId);
	//显示个人体检表列表
    public List<Examine> queryExamine(Examine examine);
    
    public int updateExamines(Examine examine);
}
