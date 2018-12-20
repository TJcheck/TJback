package great.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import great.bean.Examine;
import great.mapper.ExamineMapper;

@Service
public class ExamineService {
	
	@Autowired(required=true)
	public ExamineMapper examineMapper;
	@Transactional(rollbackFor=Exception.class)
	public boolean addExamine(List<List<Object>> list,int companyId){
		boolean flag=false;
		for(int i=1;i<list.size();i++) {
			System.out.println(list.get(i));
			Examine examine=new Examine(0, companyId, (String)list.get(i).get(0), (String)list.get(i).get(2), (String)list.get(i).get(1), (String)list.get(i).get(3));
			System.out.println(examine);
			flag=examineMapper.addExamine(examine)>0;
		}
		return flag;
	}
}
