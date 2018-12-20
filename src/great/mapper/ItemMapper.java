package great.mapper;

import java.util.List;

import great.bean.Item;

//细项映射器
public interface ItemMapper {
	// 添加细项
	public int addItem(Item item);

	// 查找所有细项
	public List<Item> queryItem(Item item);

	// 删除细项
	public int delItem(int itemId);

	// 修改细项名称
	public int updateItem(Item item);
}
