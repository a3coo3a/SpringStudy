package com.simple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simple.command.Quiz01VO;
import com.simple.command.Quiz02VO;

@Controller
@RequestMapping("/quiz")
public class QuizControlle {
	// quiz01,quiz02,quiz03 화면을 동시에 처리  : 단순히 화면만 처리
	// 반환유형을 void형으로 맞추고 맵핑값을 배열로 전달
	//@RequestMapping({"/quiz01","/quiz02","/quiz03"})
	//public void views() {}
	
	@RequestMapping("/quiz01")
	public String quiz01() {
		return "quiz/quiz01";
	}
	@RequestMapping("/sendBirth")
	public String sendBirth(@ModelAttribute("vo") Quiz01VO vo) {
		return "quiz/quiz01_ok";
	}
	
	
	@RequestMapping("/quiz02")
	public void quiz02() {}
	@RequestMapping("/join")
	public String join(@ModelAttribute("join") Quiz02VO vo) {
		return "quiz/quiz02_ok";
	}
	
	@RequestMapping("/quiz03")
	public void quiz03() {}
	@RequestMapping("/quiz03_ok")
	public void quiz03_ok() {}
	@RequestMapping("/join2")
	public String join2(@RequestParam("id") String id,
						@RequestParam("pw") String pw,
						@RequestParam("pw_check") String pw_check,
						RedirectAttributes RA) {
		if(id == null || id.equals("")) {
			RA.addFlashAttribute("msg","아이디를 확인하세요");
			return "redirect:/quiz/quiz03";
		}else if(!pw.equals(pw_check)) {
			RA.addFlashAttribute("msg","비밀번호를 확인하세요");
			return "redirect:/quiz/quiz03";
		}else {
			RA.addFlashAttribute("id",id);
			return "redirect:/quiz/quiz03_ok";
		}
	}
}
