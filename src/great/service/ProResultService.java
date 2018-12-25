package great.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import great.bean.ProResult;
import great.mapper.ProResultMapper;
@Service
public class ProResultService {
	@Autowired
	public ProResultMapper proResultMapper;
	
	public ProResult queryProResult(ProResult proResult) {
		return proResultMapper.queryProResult(proResult);
	}
}
