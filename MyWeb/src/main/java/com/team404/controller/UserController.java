package com.team404.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team404.command.UserVO;
import com.team404.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/userJoin")
	public String userJoin() {
		return "user/userJoin";
	}
	
	@RequestMapping("/userLogin")
	public String userLogin() {
		return "user/userLogin";
	}
	
	@RequestMapping("/userMypage")
	public String userMypage() {
		return "user/userMypage";
	}
	
	
	// 일반 Controller에서는 비동기 요청 메서드는 ResponseBody를 붙여야한다.
	@ResponseBody   // 응답요청을 viewResolve로 반환하는 것이 아닌, 요청이 들어오는 곳으로 Resonse Header정보를 만들어서 보내준다.
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public int idCheck(@RequestBody UserVO vo) {
		int result = userService.idCheck(vo);
		return result;
	}
	
	@RequestMapping(value = "/joinForm", method = RequestMethod.POST)
	public String joinForm(UserVO vo, RedirectAttributes RA) {
		int result = userService.join(vo);
		if(result == 1) {
			RA.addFlashAttribute("msg", "가입을 축하합니다.");
			return "redirect:/user/userLogin";			
		}else {
			RA.addFlashAttribute("msg", "가입에 실패하였습니다. 관리자에게 문의 하세요");
			return "redirect:/user/userJoin";			
			
		}
	}
	
	@ResponseBody
	@PostMapping("/loginCheck")
	public UserVO loginCheck(@RequestBody UserVO vo) {
		UserVO user = userService.login(vo); 
		return user;
	}
	
	@PostMapping("/loginForm")
	public String login(UserVO vo, HttpSession session) {
		UserVO user = userService.login(vo);
		session.setAttribute("user", user);
		return "redirect:/user/userMypage";
		
	}
	
}
