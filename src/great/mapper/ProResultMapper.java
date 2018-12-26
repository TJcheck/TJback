package great.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import great.bean.ProResult;
@Repository
public interface ProResultMapper {
	public int addProResult(ProResult proResult);
	public ProResult queryProResult(ProResult proResult);
	public int updateProResult(ProResult proResult);
	public List<ProResult> queryProResults(ProResult proResult);
}
