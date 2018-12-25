package great.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import great.bean.Examine;

@Repository
public interface ExamineMapper {
	public int addExamine(Examine examine);

	public Examine queryExamine(int examineId);

	public List<Integer> queryExamineId(int companyId);

	public int deleteExamine(int companyId);

	public List<Examine> queryExamineInfo(int companyId);
}
