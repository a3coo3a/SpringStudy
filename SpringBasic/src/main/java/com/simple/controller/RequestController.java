package com.simple.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.simple.command.LoginVO;
import com.simple.command.ReqVO;

@Controller   // 컴포넌트 스캔이 읽어서 빈으로 생성
@RequestMapping("/request")  // 공통부분을 이리로 빼는것
public class RequestController {
	
	//req_ex01 화면처리 메서드
	// void형은 mapping 경로 그대로 view에게 전달된다
	// 뷰 합성기에 의해 "/WEB-INF/views/경로.jsp" 로 반환되는 것임
//	@RequestMapping("/request/req_ex01")
//	public void req_ex01() {
//		System.out.println("실행됨");
//		
//	}
	
	@RequestMapping("/req_ex01")		// 들어가는 경로
	public String req_ex01() {
		return "request/req_ex01";  // 폴더 구조 경로
	}
	
	// basic1 요청처리(get방식만 허용)
	@RequestMapping(value = "/basic1", method=RequestMethod.GET)
	public void basic1() {
		System.out.println("basic1요청");
	}
	
	// basic2 요청처리(post방식만 허용)
	@RequestMapping(value = "/basic2", method=RequestMethod.POST)
	public void basic2() {
		System.out.println("basic2요청");
	}
	
	// basic3 요청처리(get방식만 허용) - basic1처리와 같음.
	@GetMapping("/basic3")   // 스프링 버전 4에 나온거라 안되는 경우가 있을 수 있음.
	public void basic3() {
		System.out.println("basic3요청");
	}
	
	// basic4 요청처리 (post방식만 허용) - basic2처리와 같음
	@PostMapping("/basic4")
	public void basic4() {
		System.out.println("basic4요청");
	}
	
	//===============요청파라미터 처리하기===========================
	
	@RequestMapping("/req_ex02")
	public String req_ex02() {
		return "request/req_ex02";
	}
	
	// 1번째 방법
//	@RequestMapping("/param")
//	public String param(HttpServletRequest request) {
//		
//		System.out.println(request.getParameter("id"));
//		System.out.println(request.getParameter("pw"));
//		return "request/req_ex02_result";
//	}

	// 2번째 방법
	// 파라미터 값으로 받기 때문에 전달이 되지 않을시 에러 발생
	// @RequestParam(value=＂파라미터값", required = false, defaultValue = “기본값") 
	// 값, 필수여부, 기본값 여부 이용해서 적용해야함.
//	@RequestMapping("/param")
//	public String param(@RequestParam("id") String id,
//						@RequestParam("pw") String pw,
//						@RequestParam("name") String name,
//						@RequestParam("age") int age,
//						@RequestParam(value = "inter", required = false, defaultValue = "") ArrayList<String> list) {
//		System.out.println(id);
//		System.out.println(pw);
//		System.out.println(name);
//		System.out.println(age);
//		System.out.println(list.toString());
//		
//		return "request/req_ex02_result";
//	}
	
	//3번째 방법
	@RequestMapping("/param")
	public String param(ReqVO vo) {
		System.out.println(vo.getId());
		System.out.println(vo.getPw());
		System.out.println(vo.getName());
		System.out.println(vo.getAge());
		System.out.println(vo.getInter().toString());
		return "request/req_ex02_result";
	}
	
	
	//=========================== 문제 ==========================
	// 화면처리
	@RequestMapping("/req_quiz01")
	public String req_quiz01() {
		return "request/req_quiz01";
	}
	
	// 로그인 버튼
	// 기본적으로 forword형식으로 전달됨.
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(LoginVO vo) {
		if(vo.getId().equals("abc123") && vo.getPw().equals("xxx123")) {
			return "request/req_quiz01_ok";
		}else {
			return "request/req_quiz01_no";
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
