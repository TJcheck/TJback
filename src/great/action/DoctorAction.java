package great.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import great.bean.Examine;
import great.bean.ProResult;
import great.bean.ProjectCombo;
import great.service.ComboService;
import great.service.ExamineService;
import great.service.ProResultService;
import great.util.CreateCode;
@RequestMapping("/doctor")
@Controller
public class DoctorAction {
	@Autowired
	public CreateCode createCode;
	@Autowired
	public ExamineService examineService;//个人体检
	@Autowired
	public ComboService comboService;//菜单
	@Autowired
	public ProResultService proResultService;//小结
	
	@RequestMapping(value = "/readBar.action", method = RequestMethod.POST)
	public ModelAndView readBar(@RequestParam("file") CommonsMultipartFile file) {
		//获取真实路局
		String path = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
		//获取上传文件名
		String filename = file.getOriginalFilename();
		File files=null;
		try {
			//创建保存文件数据
			files = new File(path + "/uploadFile/" + filename);
			if (!files.exists()) {
				files.mkdirs();
			}
			//保存文件
			file.transferTo(files);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Examine examine=examineService.queryExamine(Integer.parseInt(createCode.decode(files.getAbsolutePath())));
		ModelAndView mv=new ModelAndView();
		mv.addObject("examine", examine);
		mv.setViewName("back/showInfo");
		return mv;
	}
	//显示检查项目
	@RequestMapping("/showProject.action")
	public ModelAndView showProject(String examineId,int comboId) {
		List<ProjectCombo> list=comboService.queryCheckPro(comboId);//查询套餐对应的项目
		System.out.println(list);
		ModelAndView mv=new ModelAndView();
		mv.addObject("projectList", list);
		mv.addObject("projectList", examineId);
		mv.setViewName("back/check_project");
		return mv;
	}
	//更改项目小结
	@RequestMapping("/updateProResult.action")
	public ModelAndView updateProResult(int examineId,int projectId) {
		ProResult proResult=new ProResult(examineId, projectId);
		proResult=proResultService.queryProResult(proResult);
		ModelAndView mv=new ModelAndView();
		mv.addObject("proResult", proResult);
		mv.setViewName("back/proResult");
		return mv;
	}
	
}
