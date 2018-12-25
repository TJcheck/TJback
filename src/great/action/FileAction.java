package great.action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import great.service.ExamineService;
import great.util.ReadExcel;

@Controller
@RequestMapping("/file")
public class FileAction {
	@Autowired(required = true)
	public ExamineService examineService;

	// 上传文件
	@RequestMapping(value = "/fileUpload.action", method = RequestMethod.POST)
	public ModelAndView fileUpload(@RequestParam("file") CommonsMultipartFile file) {
		//获取真实路局
		String path = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
		//获取上传文件名
		String filename = file.getOriginalFilename();
		try {
			//创建保存文件数据
			File files = new File(path + "/uploadFile/" + filename);
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
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/back/login1");
		return mav;

	}

	// 读取Excel文件
	@RequestMapping("/readExcel.action")
	@ResponseBody
	public String readExcel(@RequestParam(value="fileName")String fileName, @RequestParam(value="companyId")int companyId ,@RequestParam(value="chargeId")int chargeId,int comboId) {
		List<List<Object>> list = null;
		try {
			//获取真实路径
			String path = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
			//读取Excel表格文件数据
			list = ReadExcel.readExcel(new File(path + "/uploadFile/" + fileName+".xlsx"), 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(examineService.addExamine(list, companyId,chargeId,comboId)) {
			return "success";
		}else {
			return "error";
		}
	}

	// 显示上传文件夹里的文件
	@RequestMapping("/showFile.action")
	public ModelAndView showFile() {
		//获取真实路径
		String path = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
		//获取上传文件夹文件数据
		File file = new File(path + "/uploadFile");
		File[] s = file.listFiles();
		for (File f : s) {
			System.out.println(f.getName());
			System.out.println(new Date(f.lastModified()).toString());
		}

		return null;

	}

	// 下载文件
	@RequestMapping("/downExcel.action")
	public ResponseEntity<byte[]> downExcel() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		String path = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
		File file = new File(path + "/downfile/a1234.xlsx");
		//设置正文的媒体类型
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		//Content-Disposition创建"multipart/form-data"请求时设置标头
		headers.setContentDispositionFormData("attachment", file.getName());
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	}
	//导出Excel表格
	@RequestMapping("/exportExcelModel.action")
	public ModelAndView exportExcelModel(HttpServletResponse response) {
		// 创建HSSFWorkbook对象(excel的文档对象)
		HSSFWorkbook wb = new HSSFWorkbook();
		// 建立新的sheet对象（excel的表单）
		HSSFSheet sheet = wb.createSheet("成绩表");
		sheet.setColumnWidth(0, 4000);
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 4000);
		// 创建HSSFCellStyle(单元格样式)
		HSSFCellStyle style = wb.createCellStyle();
		HSSFCellStyle style1 = wb.createCellStyle();
		// 创建HSSFFont(字体样式)
		HSSFFont  fontStyle=wb.createFont();
		//设置字体高度
		fontStyle.setFontHeightInPoints((short)20);  
		//设置粗体
		fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 设置这些样式
		style1.setFont(fontStyle);
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中 
		style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中 
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框    
		// 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1 = sheet.createRow(0);
		// 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell = row1.createCell(0);
		cell.setCellStyle(style1);
		
		// 设置单元格内容
		cell.setCellValue("体检人员名单");
		// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
		// 在sheet里创建第二行
		HSSFRow row2 = sheet.createRow(1);
		// 创建单元格并设置单元格内容
		row2.createCell(0).setCellValue("姓名");
		row2.createCell(1).setCellValue("年龄");
		row2.createCell(2).setCellValue("性别");
		row2.createCell(3).setCellValue("手机号码");
		row2.getCell(0).setCellStyle(style);
		row2.getCell(1).setCellStyle(style);
		row2.getCell(2).setCellStyle(style);
		row2.getCell(3).setCellStyle(style);
		// 在sheet里创建第三行
		HSSFRow row3 = sheet.createRow(2);
		row3.createCell(0).setCellValue("李明");
		row3.createCell(1).setCellValue("As178");
		row3.createCell(2).setCellValue(87);
		row3.createCell(3).setCellValue(78);
		row3.getCell(0).setCellStyle(style);
		row3.getCell(1).setCellStyle(style);
		row3.getCell(2).setCellStyle(style);
		row3.getCell(3).setCellStyle(style);
		// 输出Excel文件
		OutputStream output;
		try {
			output = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition",
					"attachment; filename=" + new String("模板".getBytes("utf-8"), "iso-8859-1") + ".xls");
			response.setContentType("application/msexcel;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			wb.write(output);
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}
