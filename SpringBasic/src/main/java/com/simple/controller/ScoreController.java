package com.simple.controller;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.simple.command.ScoreVO;
import com.simple.service.ScoreService;
import com.simple.service.ScoreServiceImpl;

@Controller
@RequestMapping("/service")
public class ScoreController {
	// 1번째 방법
	// 전역변수
	// ScoreService service = new ScoreServiceImpl();

	// 2번째 방법 
	// 스프링 컨테이너에 직접 bean으로 생성하고 자동 주입 명령
	// servlet-context.xml에 작성
	// <beans:bean id="service" class="com.simple.service.ScoreServiceImpl"/> 
	// @Autowired : 타입으로 찾고 없으면 이름으로 찾는다.
	//@Resource(name="service")
	//private ScoreService service;
	
	// 3번째 방법
	@Autowired
	@Qualifier("scoreService")
	private ScoreService service;
	
	// 점수 입력 화면 처리
	@RequestMapping("/scoreRegist")
	public String scoreRegist() {
		return "service/scoreRegist";
	}
	
	// 점수 입력 폼처리
	@RequestMapping("/scoreForm")
	public String scoreForm(ScoreVO vo) {
		service.scoreRegist(vo);
		return "service/scoreResult";
	}
	
	// 점수 리스트 화면
	@RequestMapping("/scoreList")
	public String scoreList(Model model) {
		ArrayList<ScoreVO> list = service.getList();
		model.addAttribute("list", list);
		return "service/scoreList";
	}
	
	// 점수삭제 요청
	// @RequestParam("num") int index 대신 ScoreVO vo 사용해도 됨.
	@RequestMapping("/scoreDelete")
	public String scoreDelete(@RequestParam("num") int num) {
		service.scoreDelete(num);
		return "redirect:/service/scoreList";
	}
	
}
