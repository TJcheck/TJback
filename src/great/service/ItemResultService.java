package great.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import great.bean.ItemResult;
import great.mapper.ItemResultMapper;
@Service
public class ItemResultService {
	@Autowired
	public ItemResultMapper itemResultMapper;
	public List<ItemResult> queryItemResult(ItemResult itemResult){
		return itemResultMapper.queryItemResult(itemResult);
	}
}
