package great.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import great.bean.Item;
import great.mapper.ItemMapper;
@Service
public class ItemService {
	// 注入细项映射器
	@Resource
    private ItemMapper itemMapper;
	
	// 添加细项
	public boolean addItem(Item item) {
		return itemMapper.addItem(item) > 0;
	}

	// 查找细项
	public List<Item> queryItem(Item item) {
		return itemMapper.queryItem(item);
	}

	// 更新细项
	public boolean updateItem(Item item) {
		return itemMapper.updateItem(item) > 0;
	}

	// 删除细项
	public boolean delItem(int itemId) {
		return itemMapper.delItem(itemId)> 0;
	}


}
