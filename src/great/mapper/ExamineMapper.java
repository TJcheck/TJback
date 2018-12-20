package great.mapper;

import org.springframework.stereotype.Repository;

import great.bean.Examine;
@Repository
public interface ExamineMapper {
	public int addExamine(Examine examine);
}
