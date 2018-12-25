package great.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import great.bean.Charge;
import great.bean.Examine;
import great.bean.ProResult;
import great.bean.ProjectCombo;
import great.mapper.ChargeMapper;
import great.mapper.ComboMapper;
import great.mapper.ExamineMapper;
import great.mapper.ProResultMapper;

@Service
public class ExamineService {
	
	@Autowired(required=true)
	public ExamineMapper examineMapper;
	@Autowired(required=true)
	public ChargeMapper chargeMapper;
	@Autowired
	public ComboMapper comboMapper;
	@Autowired
	public ProResultMapper proResultMapper;
	
	@Transactional(rollbackFor=Exception.class)
	public boolean addExamine(List<List<Object>> list,int companyId,int chargeId,int comboId){
		boolean flag=false;
		examineMapper.deleteExamine(companyId);
		for(int i=1;i<list.size();i++) {
			Examine examine=new Examine(0, companyId, (String)list.get(i).get(0), (String)list.get(i).get(2), (String)list.get(i).get(1), (String)list.get(i).get(3),comboId);
			examineMapper.addExamine(examine);
		}
		Charge charge=new Charge();
		charge.setChargeId(chargeId);//设置记账ID
		charge.setReservationStatus(5);//将状态设置为已导入
		chargeMapper.updateReservation(charge);
		
		
		List<Integer> idList=examineMapper.queryExamineId(companyId);
		ProjectCombo projectCombo=new ProjectCombo();
		projectCombo.setComboId(comboId);
		List<ProjectCombo> Projectlist=comboMapper.queryProjects(projectCombo);
		for(int i=0;i<idList.size();i++) {
			for(ProjectCombo project:Projectlist) {
				ProResult proResult=new ProResult(idList.get(i), project.getProjectId());
				proResultMapper.addProResult(proResult);
			}
		}
		
		flag=true;
		return flag;
	}
	public Examine queryExamine(int examineId) {
		return examineMapper.queryExamine(examineId);
	};

}
