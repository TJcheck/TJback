package great.service;

import java.util.List;

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
	public boolean updateProResult(ProResult proResult) {
		return proResultMapper.updateProResult(proResult)>0;
	}
	public List<ProResult> queryProResults(ProResult proResult){
		return proResultMapper.queryProResults(proResult);
	}
}
