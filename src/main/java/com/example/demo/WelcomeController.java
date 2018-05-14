package com.example.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class WelcomeController {

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("message", this.message);
		return "welcome";
	}
	
	@RequestMapping("/hello")
	public String welcome2(Map<String, Object> model) {
		model.put("message", this.message);
		return "hello";
	}
	
	@RequestMapping(value="/community/imageUpload",  method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void communityImageUpload(HttpServletRequest request, HttpServletResponse response, FileBean fileBean, @RequestParam(value = "CKEditorFuncNum", required = false, defaultValue = "") String _CKEditorFuncNum) 
	throws Exception {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset-utf-8");
		
		System.out.println("CKEditorFuncNum : " + request.getAttribute("CKEditorFuncNum"));
		System.out.println("_CKEditorFuncNum : " + _CKEditorFuncNum);
		
		
		System.out.println("여기 안오는겨??????????");

		OutputStream out = null;
		PrintWriter printWriter = null;
		
		
		
		MultipartFile upload = fileBean.getUpload(); 
	    String filename = "";
	    String CKEditorFuncNum = "";
	    
	    String fileName = upload.getOriginalFilename();
		byte[] bytes = upload.getBytes();
		String uploadPath = "/Users/yjkim/Documents/workspace-sts-3.9.3.RELEASE/springboot-mvc-sample/src/main/resources/static/upload/" + fileName;
	    
	    if (upload != null) { 
	        filename = upload.getOriginalFilename(); 
	        fileBean.setFilename(filename); 
	        CKEditorFuncNum = fileBean.getCKEditorFuncNum(); 
	        try { 
	            File file = new File(uploadPath + filename); 
	            //log.info(root_path + attach_path + filename); 
	            upload.transferTo(file); 
	        } catch (IOException e) { 
	            e.printStackTrace(); 
	        } 
	    } 
	    
		printWriter = response.getWriter();
		String fileUrl = "http://localhost:8080/upload/" +fileName; //url 경로

		printWriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
	               + 1
	               + ",'"
	               + fileUrl
	               + "','이미지를 업로드 하였습니다.'"
	               + ")</script>");
	       printWriter.flush();
	}
	
	@RequestMapping(value = "/ckeditorImageUpload",  method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String ckeditorImageUpload(FileBean fileBean,HttpServletRequest request, Model model) { 
		
		System.out.println("fileBean : " + fileBean.toString());
		
	    HttpSession session = request.getSession(); 
	    String root_path = session.getServletContext().getRealPath("/"); 
	    String attach_path = "/upload/";
	    String uploadPath = "/Users/yjkim/Documents/workspace-sts-3.9.3.RELEASE/springboot-mvc-sample/src/main/resources/static/upload/";
	    
	    MultipartFile upload = fileBean.getUpload(); 
	    String filename = "";
	    String CKEditorFuncNum = "";
	    
	    if (upload != null) { 
	        filename = upload.getOriginalFilename(); 
	        fileBean.setFilename(filename); 
	        CKEditorFuncNum = fileBean.getCKEditorFuncNum(); 
	        try { 
	            File file = new File(uploadPath + filename); 
	            //log.info(root_path + attach_path + filename); 
	            upload.transferTo(file); 
	        } catch (IOException e) { 
	            e.printStackTrace(); 
	        } 
	    } 
	    
	    String file_path = attach_path + filename; 
	    model.addAttribute("file_path", file_path); 
	    model.addAttribute("CKEditorFuncNum", 1);
	    return "editor";
	}
	
	@RequestMapping(value = "/fileUpload",  method = RequestMethod.POST)
    public String fileUpload(@ModelAttribute("fileUploadVO") FileUploadVO fileUploadVO , HttpServletRequest request , Model model){
		
		System.out.println("fileUploadVO : " + fileUploadVO.toString());
		
        HttpSession session = request.getSession();
        String rootPath = session.getServletContext().getRealPath("/");
        String attachPath = "upload/";
        
        System.out.println("rootPath : " + rootPath);
 
        MultipartFile upload = fileUploadVO.getUpload();
        String filename = "";
        String CKEditorFuncNum = "";
         
        if(upload != null){
            filename = upload.getOriginalFilename();
            fileUploadVO.setFilename(filename);
            CKEditorFuncNum = fileUploadVO.getCKEditorFuncNum();
            try{
                File file = new File(rootPath + attachPath + filename);
                upload.transferTo(file);
            }catch(IOException e){
                e.printStackTrace();
            }  
        }
            model.addAttribute("filePath",attachPath + filename);          //결과값을
            model.addAttribute("CKEditorFuncNum",CKEditorFuncNum);//jsp ckeditor 콜백함수로 보내줘야함
        return "fileUploadComplete";
    }
}
