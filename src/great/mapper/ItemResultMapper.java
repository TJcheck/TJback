package great.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import great.bean.ItemResult;
@Repository
public interface ItemResultMapper {
	//添加细项结果
	public int addItemResult(ItemResult itemResult);
	//查询细项结果
	public List<ItemResult> queryItemResult(ItemResult itemResult);
}
