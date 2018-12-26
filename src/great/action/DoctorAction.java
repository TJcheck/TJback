package great.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import great.bean.Examine;
import great.bean.ItemResult;
import great.bean.ProResult;
import great.bean.ProjectCombo;
import great.bean.ProjectItem;
import great.service.ComboService;
import great.service.ExamineService;
import great.service.ItemResultService;
import great.service.ProResultService;
import great.service.ProjectService;
import great.util.CreateCode;

@RequestMapping("/doctor")
@Controller
public class DoctorAction {
	@Autowired
	public CreateCode createCode;
	@Autowired
	public ExamineService examineService;// 个人体检
	@Autowired
	public ComboService comboService;// 菜单
	@Autowired
	public ProResultService proResultService;// 小结
	@Autowired
	public ProjectService projectService;
	@Autowired
	public ItemResultService itemResultService;

	// 识别条形码显示体检人员信息
	@RequestMapping(value = "/readBar.action", method = RequestMethod.POST)
	public ModelAndView readBar(@RequestParam("file") CommonsMultipartFile file) {
		// 获取真实路局
		String path = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
		// 获取上传文件名
		String filename = file.getOriginalFilename();
		File files = null;
		try {
			// 创建保存文件数据
			files = new File(path + "/uploadFile/" + filename);
			if (!files.exists()) {
				files.mkdirs();
			}
			// 保存文件
			file.transferTo(files);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Examine examine = examineService.queryExamine(Integer.parseInt(createCode.decode(files.getAbsolutePath())));
		ModelAndView mv = new ModelAndView();
		mv.addObject("examine", examine);
		mv.setViewName("back/showInfo");
		return mv;
	}

	@RequestMapping("/backInfo.action")
	public ModelAndView backInfo(int examineId) {
		Examine examine = examineService.queryExamine(examineId);
		ModelAndView mv = new ModelAndView();
		mv.addObject("examine", examine);
		mv.setViewName("back/showInfo");
		return mv;
	}

	// 显示检查项目
	@RequestMapping(value = "/showProject.action", method = RequestMethod.GET)
	public ModelAndView showProject(String examineId, int comboId) {
		List<ProjectCombo> list = comboService.queryCheckPro(comboId);// 查询套餐对应的项目
		ModelAndView mv = new ModelAndView();
		mv.addObject("projectList", list);
		mv.addObject("examineId", examineId);
		mv.addObject("comboId", comboId);
		mv.setViewName("back/check_project");
		return mv;
	}

	// 显示项目小结
	@RequestMapping("/showProResult.action")
	public ModelAndView showProResult(int examineId, int projectId, int comboId, int projectType) {
		ProResult proResult = new ProResult(examineId, projectId);
		proResult = proResultService.queryProResult(proResult);
		ItemResult itemResult = new ItemResult();
		itemResult.setExamineId(examineId);
		itemResult.setProjectId(projectId);
		List<ItemResult> lists = itemResultService.queryItemResult(itemResult);
		ModelAndView mv = new ModelAndView();
		mv.addObject("proResult", proResult);
		mv.addObject("itemResultList", lists);
		mv.addObject("examineId", examineId);
		mv.addObject("comboId", comboId);
		mv.addObject("projectType", projectType);
		mv.setViewName("back/proResult");
		return mv;
	}

	// 修改项目小结
	@RequestMapping("/updateResult.action")
	@ResponseBody
	public String updateResult(int examineId, int projectId, int adminId, String summary) {
		ProResult proResult = new ProResult(0, examineId, projectId, null, 12, null, adminId, summary);
		if (proResultService.updateProResult(proResult)) {
			return "success";
		} else {
			return "error";
		}
	}

	// 显示项目小结信息
	@RequestMapping(value = "/showProResult.action", method = RequestMethod.POST)
	public ModelAndView showProResult(@RequestParam("file") CommonsMultipartFile file) {
		// 获取真实路局
		String path = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
		// 获取上传文件名
		String filename = file.getOriginalFilename();
		File files = null;
		try {
			// 创建保存文件数据
			files = new File(path + "/uploadFile/" + filename);
			if (!files.exists()) {
				files.mkdirs();
			}
			// 保存文件
			file.transferTo(files);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int examineId = Integer.parseInt(createCode.decode(files.getAbsolutePath()));
		ProResult proResult = new ProResult();
		proResult.setExamineId(examineId);
		List<ProResult> list = proResultService.queryProResults(proResult);
		ItemResult itemResult = new ItemResult();
		itemResult.setExamineId(examineId);
		List<ItemResult> itemResultList = itemResultService.queryItemResult(itemResult);
		ModelAndView mv = new ModelAndView();
		System.out.println(itemResultList);
		mv.addObject("proResultList", list);
		mv.addObject("itemResultList", itemResultList);
		mv.addObject("examineId", examineId);
		mv.setViewName("back/summary");
		return mv;
	}
	//修改体检个人表总结，建议，生活指导
	@RequestMapping("/updateSummary.action")
	@ResponseBody
	public String updateSummary(String conclusion, String exSuggest, String exGuide, int examineId, int adminId) {
		Examine examine = new Examine();
		examine.setConclusion(conclusion);
		examine.setExSuggest(exSuggest);
		examine.setExGuide(exGuide);
		examine.setExamineId(examineId);
		examine.setAdminId(adminId);
		examine.setExStatus(12);
		if (examineService.updateExamines(examine)) {
			return "success";
		} else {
			return "error";
		}
	}
}
