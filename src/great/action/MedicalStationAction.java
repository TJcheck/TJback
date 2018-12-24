package great.action;
//体检站

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import great.bean.Charge;
import great.bean.PageInfo;
import great.service.ChargeService;
@Controller
@RequestMapping("/medicalStation")
public class MedicalStationAction {
	@Autowired
	public ChargeService chargeService;
	
	//查询记账表
	@RequestMapping("/queryCharge.action")
	public ModelAndView queryCharge() {
		ModelAndView mav = new ModelAndView();
		// 第一次跳转当前页数设置为1
		int currentPage = 1;
		// 分页工具分页
		Page<Object> page = PageHelper.startPage(currentPage, 5);
		// 查询项目数据
		Charge charge = new Charge();
		List<Charge> chargeList = chargeService.queryCharge(charge);
		currentPage = page.getPageNum();// 当前页数
		int totalPage = page.getPages();// 获得总页数
		int totalNum = (int) page.getTotal();// 总记录数
		// 如果什么都没有查到，当前页变为0
		if (totalNum == 0) {
			currentPage = 0;
		}
		Map<String, Object> data = new HashMap<String, Object>();// 查询结果数据
		data.put("chargeList", chargeList);
		PageInfo pageInfo = new PageInfo(currentPage, totalPage, totalNum, data);
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("/back/billing");
		return mav;
		
	}
	// ajax分页查询记账信息
	@RequestMapping(value = "/chargeListPaging.action")
	@ResponseBody
	public PageInfo comboListPaging(@RequestParam(value="currentPage") int currentPage,@RequestParam(value="comboName") String comboName) {
		// 分页工具分页每页五条
		Page<Object> page = PageHelper.startPage(currentPage, 5);
		// 查询角色数据
		Charge charge = new Charge();
		if (comboName != null && !comboName.equals("")) {
			charge.setComboName(comboName);
		}
		List<Charge> chargeList = chargeService.queryCharge(charge);
		currentPage = page.getPageNum();// 当前页数
		int totalPage = page.getPages();// 获得总页数
		int totalNum = (int) page.getTotal();// 总记录数
		// 如果什么都没有查到，当前页变为0
		if (totalNum == 0) {
			currentPage = 0;
		}
		Map<String, Object> data = new HashMap<String, Object>();// 查询结果数据
		data.put("chargeList", chargeList);
		PageInfo pageInfo = new PageInfo(currentPage, totalPage, totalNum, data);
		return pageInfo;
	}
	//确认预约
	@RequestMapping(value = "/okAppointment.action")
	@ResponseBody
	public String okAppointment(int chargeId,String examineTime) {
		Charge charge=new Charge();
		charge.setChargeId(chargeId);//填入记账ID
		charge.setReservationStatus(3);//将状态更改为已确认状态
		charge.setExamineTime(examineTime);
		if(chargeService.updateReservation(charge)) {
			return "success";
		}else {
			return "error";
		}
	}
}
