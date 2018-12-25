package great.action;
//体检站

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import great.bean.Charge;
import great.bean.Examine;
import great.bean.PageInfo;
import great.bean.ProjectCombo;
import great.service.ChargeService;
import great.service.ComboService;
import great.service.ExamineService;
import great.util.CreateCode;

@Controller
@RequestMapping("/medicalStation")
public class MedicalStationAction {
	@Autowired
	public ChargeService chargeService;
	@Autowired
	public ExamineService examineService;// 个人体检表信息
	@Autowired
	public ComboService comboService;

	// 个人体检表
	// 显示条形码
	@RequestMapping(value = "/barCode.action")
	public void BarCode(HttpServletResponse resp, String keyCode) {
		String url = keyCode;
		if (url != null && !"".equals(url)) {
			ServletOutputStream stream = null;
			try {

				int width = 200;// 图片的宽度
				int height = 50;// 高度
				stream = resp.getOutputStream();
				CreateCode code = new CreateCode();
				code.encode(url, width, height, stream);
				// code.qrcode(url, width, height, stream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (stream != null) {
					try {
						stream.flush();
						stream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	// 跳转到导检单页
	@RequestMapping(value = "/examineDetails.action")
	public ModelAndView examineDetails(HttpServletResponse resp, int examineId, int currentPage) {
		ModelAndView mav = new ModelAndView();
		// 查询个人信息 项目信息
		Examine examine = new Examine();
		examine.setExamineId(examineId);
		List<Examine> examines = examineService.queryExamine(examine);
		int comboId = examines.get(0).getComboId();
		List<ProjectCombo> pCombos = comboService.queryCheckPro(comboId);
		mav.addObject("details", examines.get(0));
		mav.addObject("dataList", pCombos);
		mav.addObject("currentPage", currentPage);
		mav.setViewName("/back/examine_details");
		// 传送条形码
		return mav;
	}

	// 跳转到个人体检页面
	@RequestMapping(value = "/examineManagerPage.action")
	public ModelAndView exManagerPage(int currentPage) {
		ModelAndView mav = new ModelAndView();
		if (currentPage == 0) {
			currentPage = 1;
		}
		// 分页工具
		Page<Object> page = PageHelper.startPage(currentPage, 5);
		// 查询项目数据
		Examine examine = new Examine();
		List<Examine> exList = examineService.queryExamine(examine);
		currentPage = page.getPageNum();// 当前页数
		int totalPage = page.getPages();// 获得总页数
		int totalNum = (int) page.getTotal();// 总记录数
		if (totalNum == 0) {
			currentPage = 0;
		}
		Map<String, Object> data = new HashMap();
		data.put("examineList", exList);
		PageInfo pageInfo = new PageInfo(currentPage, totalPage, totalNum, data);
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName("/back/examine_manage");
		return mav;
	}

	// ajax分页查询套餐信息
	@RequestMapping(value = "/examineListPaging.action")
	@ResponseBody
	public PageInfo comboListPaging(int currentPage, int onePage, String exName) {
		// 分页工具分页每页五条
		Page<Object> page = PageHelper.startPage(currentPage, onePage);
		// 查询角色数据
		Examine examine = new Examine();
		if (exName != null && !exName.equals("")) {
			examine.setExName(exName);
		}
		List<Examine> examineList = examineService.queryExamine(examine);
		currentPage = page.getPageNum();// 当前页数
		int totalPage = page.getPages();// 获得总页数
		int totalNum = (int) page.getTotal();// 总记录数
		// 如果什么都没有查到，当前页变为0
		if (totalNum == 0) {
			currentPage = 0;
		}
		Map<String, Object> data = new HashMap<String, Object>();// 查询结果数据
		data.put("examineList", examineList);
		PageInfo pageInfo = new PageInfo(currentPage, totalPage, totalNum, data);
		return pageInfo;
	}

	// 查询记账表
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
	public PageInfo comboListPaging(@RequestParam(value = "currentPage") int currentPage,
			@RequestParam(value = "comboName") String comboName) {
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

	// 确认预约
	@RequestMapping(value = "/okAppointment.action")
	@ResponseBody
	public String okAppointment(int chargeId, String examineTime) {
		Charge charge = new Charge();
		charge.setChargeId(chargeId);// 填入记账ID
		charge.setReservationStatus(3);// 将状态更改为已确认状态
		charge.setExamineTime(examineTime);
		if (chargeService.updateReservation(charge)) {
			return "success";
		} else {
			return "error";
		}
	}
}
