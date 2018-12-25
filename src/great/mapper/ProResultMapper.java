package great.mapper;

import org.springframework.stereotype.Repository;

import great.bean.ProResult;
@Repository
public interface ProResultMapper {
	public int addProResult(ProResult proResult);
	public ProResult queryProResult(ProResult proResult);
}
