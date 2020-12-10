package com.simple.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simple.command.ReqVO;

@Controller
@RequestMapping("/response")
public class ResponseContorller {
	@RequestMapping("/res_ex01")
	public String res_ex01() {
		return "response/res_ex01";
	}
	
	// Model전달자 - 메서드 매개변수로 Model or ModelMap 선언 
	// : addAttribute
	@RequestMapping("/res_ex02")
	public String res_ex02(Model model) {
		// serveerTime이라는 이름으로 날짜를 저장
		model.addAttribute("serverTime", new Date()); 
		model.addAttribute("name", "홍길동");
		model.addAttribute("addr", "서울시");
		return "response/res_ex02";
	}
	
	// ModelAndView 객체
	// - Model에 대한 정보와, 화면에 대한 정보를 한번에 지정
	@RequestMapping("/res_ex03")
	public ModelAndView res_ex03(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("serverTime", new Date());
		mv.addObject("name", "이순신");
		mv.addObject("addr", "통영");
		// 화면정보 저장 : 이리로 이동하는 거야
		mv.setViewName("response/res_ex03");
		return mv;
	}
	
	// @ModelAttrivute()
	// - request, model이 합쳐진 형태
	// 화면에서 전달받은 값을 다음 화면으로 그대로 전달
	@RequestMapping("/res_ex04")
	public String res_ex04(@ModelAttribute("num") String num,
						   @ModelAttribute("name") String name) {
		System.out.println("넘어온 값 : " + num);
		System.out.println("넘어온 값 : " + name);
		return "response/res_ex04";
	}
	
	// info 이름으로 vo객체가 다음화면으로 전달
	@RequestMapping("/res_ex05")
	public String res_ex05(@ModelAttribute("info") ReqVO vo) {  // vo에 담기지만, 다음화면에서는info라는 이름으로
		System.out.println(vo.getId());
		System.out.println(vo.getName());
		
		return "response/res_ex05";
	}
	
	//============== 리다이렉트와 리다이렉트 어트리뷰트 ===========
	//화면처리
	@RequestMapping("/res_redirect")
	public String res_redirect() {
		return "response/res_redirect";
	}
	
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String login(@RequestParam("id") String id,
//						@RequestParam("pw") String pw,
//						Model model) {
//		if(id.equals(pw)) { // 로그인 성공으로 간주
//			// 성공시 홈화면
//			return "response/res_redirect_ok";
//		} else {			
//			// 리다이렉트 이기 때문에 값이 넘어가지못함.
//			model.addAttribute("msg", "비밀번호를 확인하세요");
//			// 다시 컨트롤러를 태워서 보내는거야
//			return "redirect:/response/res_redirect";  // 절대경로 그래서 앞에 "/"가 있어요
//		}
//	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("id") String id,
						@RequestParam("pw") String pw,
						RedirectAttributes RA) {
		if(id.equals(pw)) { // 로그인 성공으로 간주
			// 성공시 홈화면
			return "response/res_redirect_ok";
		} else {
			// "redirect:절대경로" - 다시 컨트롤러를 타게함
			// 이때 화면에 1회성 데이터로 값을 전달하고 싶으면
			// RedirectAttributes객체를 사용하면 된다.
			// 리다이렉트 시에 모델을 전달할 수 있음.
			RA.addFlashAttribute("msg", "비밀번호를 확인하세요");
			// 다시 컨트롤러를 태워서 보내는거야
			return "redirect:/response/res_redirect";  // 절대경로 그래서 앞에 "/"가 있어요
		}
	}
	
	//=============== 문제 ===================
	@RequestMapping("/res_quiz01")
	public String res_quiz01() {
		return "response/res_quiz01";
	}
	@RequestMapping("/res_quiz02")
	public String res_quiz02() {
		return "response/res_quiz02";
	}
	@RequestMapping("/res_quiz03")
	public String res_quiz03() {
		return "response/res_quiz03";
	}
	@RequestMapping("/res_login")
	public String res_login(@RequestParam("id") String id,
							@RequestParam("pw") String pw,
							RedirectAttributes RA) {
		
		if(id.equals("kim12") && pw.equals("1234")) {
			RA.addFlashAttribute("id", id);
			return "redirect:/response/res_quiz02";
		}else {
			RA.addFlashAttribute("id", id);
			return "redirect:/response/res_quiz03";
		}
		
	}
	
	
}
