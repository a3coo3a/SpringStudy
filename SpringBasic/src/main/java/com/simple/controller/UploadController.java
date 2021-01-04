package com.simple.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.simple.command.MultiUploadVO;
import com.simple.command.UploadVO;

@Controller
@RequestMapping("/fileupload")
public class UploadController {
	
	public static final String UPLOAD_PATH = "D:\\upload";

	// 화면처리
	@RequestMapping("/upload")
	public String upload() {
		return "fileupload/upload";
	}
	
	@RequestMapping("/upload_ok")
	public String upload_ok(@RequestParam("file") MultipartFile file) {
		
		
		try {
			String fileRealName = file.getOriginalFilename();   // 파일의 실제 이름
			long size = file.getSize();  // 파일크기
			String fileExtention = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
			
			System.out.println("파일실제이름 : "+fileRealName);
			System.out.println("파일 사이즈 : "+size);
			System.out.println("파일 확장자 : "+fileExtention);
			
			File saveFile = new File(UPLOAD_PATH + "\\" + fileRealName);  // 실제파일이저장될 경로
			file.transferTo(saveFile);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "fileupload/upload_ok";
	}
	
	@RequestMapping("/upload_ok2")
	public String upload_ok2(MultipartHttpServletRequest files) {
		//System.out.println(files.toString());
		
		List<MultipartFile> list = files.getFiles("files");  // ()안에 input의 name이 들어감.
		try {
			for(int i = 0; i < list.size(); i++) {
				String fileRealName = list.get(i).getOriginalFilename();   // 파일의 실제 이름
				long size = list.get(i).getSize();  // 파일크기
				String fileExtention = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
				
				System.out.println("파일실제이름 : "+fileRealName);
				System.out.println("파일 사이즈 : "+size);
				System.out.println("파일 확장자 : "+fileExtention);
				
				File saveFile = new File(UPLOAD_PATH + "\\" + fileRealName);  // 실제파일이저장될 경로
				list.get(i).transferTo(saveFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "fileupload/upload_ok";
	}
	
	@RequestMapping("/upload_ok3")
	public String upload_ok3(@RequestParam("file") List<MultipartFile> list) {
		
		try {
			for(int i = 0; i < list.size(); i++) {
				if(!list.get(i).isEmpty()) {  // 비어있으면 true
					String fileRealName = list.get(i).getOriginalFilename();   // 파일의 실제 이름
					long size = list.get(i).getSize();  // 파일크기
					String fileExtention = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
					
					System.out.println("파일실제이름 : "+fileRealName);
					System.out.println("파일 사이즈 : "+size);
					System.out.println("파일 확장자 : "+fileExtention);
					
					File saveFile = new File(UPLOAD_PATH + "\\" + fileRealName);  // 실제파일이저장될 경로
					list.get(i).transferTo(saveFile);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "fileupload/upload_ok";
	}
	
	
	
//	@RequestMapping("/upload_ok4")
//	public String upload_ok4(@RequestParam("name") List<String> names, 
//						     @RequestParam("file") List<MultipartFile> files) {
//		
//		
//		return "fileupload/upload_ok";
//	}
	
	@RequestMapping("/upload_ok4")
	public String upload_ok4(MultiUploadVO vo) {
		
		//System.out.println(vo.getList().toString());
		
		List<UploadVO> list = vo.getList();
		try {
			for(int i = 0; i < list.size(); i++) {
				
					String name = list.get(i).getName();
					String fileRealName = list.get(i).getFile().getOriginalFilename();   // 파일의 실제 이름
					long size = list.get(i).getFile().getSize();  // 파일크기
					String fileExtention = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
					
					System.out.println("텍스트명 : "+name);
					System.out.println("파일실제이름 : "+fileRealName);
					System.out.println("파일 사이즈 : "+size);
					System.out.println("파일 확장자 : "+fileExtention);
					
					File saveFile = new File(UPLOAD_PATH + "\\" + fileRealName);  // 실제파일이저장될 경로
					list.get(i).getFile().transferTo(saveFile);
				
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "fileupload/upload_ok";
	}
	
	
}
