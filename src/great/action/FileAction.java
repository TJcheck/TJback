package great.action;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import great.utill.ReadExcel;

@Controller
@RequestMapping("/file")
public class FileAction {
	@RequestMapping(value = "/fileUpload.action", method = RequestMethod.POST)
	public ModelAndView fileUpload(@RequestParam("file") CommonsMultipartFile file) {
		String path = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
		String filename = file.getOriginalFilename();
		try {
			File files = new File(path + "/uploadFile/" + filename);
			if (!files.exists()) {
				files.mkdirs();
			}
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

	@RequestMapping("/readExcel.action")
	public ModelAndView readExcel(String fileName) {
		List<List<Object>> list = null;
		try {
			String path = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
			list = ReadExcel.readExcel(new File(path + "/uploadFile/" + "a1234.xlsx"), 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(list);
		return null;
	}

	@RequestMapping("/showFile.action")
	public ModelAndView showFile() {
		String path = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
		File file = new File(path + "/uploadFile");
		File[] s = file.listFiles();
		for (File f : s) {
			System.out.println(f.getName());
			System.out.println(new Date(f.lastModified()).toString());
		}
		return null;

	}
	@RequestMapping("/downExcel.action")
	public ResponseEntity<byte[]> downExcel() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		String path = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
		File file = new File(path + "/downfile/a1234.xlsx");
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		headers.setContentDispositionFormData("attachment", file.getName());
		 return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
                 headers, HttpStatus.CREATED);

	}
}
