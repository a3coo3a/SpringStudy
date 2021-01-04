package com.team404.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
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
	public String userMypage(HttpSession session, Model model) {
		
		// 조인 데이터에 대한 처리바법
		UserVO vo = (UserVO)session.getAttribute("userVO");
		String userId = vo.getUserId();
		
		// 1:N 관계 맵핑으로 처리
		UserVO userInfo = userService.getInfo(userId);
		System.out.println(userInfo);
		
		model.addAttribute("userInfo", userInfo);
		
		return "user/userMypage";
	}
	
	
	// 일반 Controller에서는 비동기 요청 메서드는 ResponseBody를 붙여야한다.
	@ResponseBody   // 응답요청을 viewResolve로 반환하는 것이 아닌, 요청이 들어오는 곳으로 Resonse Header정보를 만들어서 보내준다.
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public int idCheck(@RequestBody UserVO vo) {
		int result = userService.idCheck(vo);
		return result;
	}
	//me
//	@RequestMapping(value = "/joinForm", method = RequestMethod.POST)
//	public String joinForm(UserVO vo, RedirectAttributes RA) {
//		int result = userService.join(vo);
//		if(result == 1) {
//			RA.addFlashAttribute("msg", "가입을 축하합니다.");
//			return "redirect:/user/userLogin";			
//		}else {
//			RA.addFlashAttribute("msg", "가입에 실패하였습니다. 관리자에게 문의 하세요");
//			return "redirect:/user/userJoin";			
//			
//		}
//	}
	//ssam
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(UserVO vo,RedirectAttributes RA) {
		int result = userService.join(vo);
		if(result == 1) { // 성공
			RA.addFlashAttribute("msg", "가입을 축하합니다!");
		}else {  // 실패
			RA.addFlashAttribute("msg", "가입에 실패했습니다. 다시 시도하세요");
		}
			
		return "redirect:/user/userLogin";
	}
	
	//me
//	@ResponseBody
//	@PostMapping("/loginCheck")
//	public UserVO loginCheck(@RequestBody UserVO vo) {
//		UserVO user = userService.login(vo); 
//		return user;
//	}
//	
//	@PostMapping("/loginForm")
//	public String login(UserVO vo, HttpSession session) {
//		UserVO user = userService.login(vo);
//		session.setAttribute("user", user);
//		return "redirect:/user/userMypage";
//		
//	}
		
	//ssam
//	@RequestMapping(value = "login", method = RequestMethod.POST)   // value는 /생략가능
//	public String login(UserVO vo, Model model,
//						HttpSession session) {
//		// 로그인 성공시 회원정보를 받아오고, 로그인 실패시 null을 반환
//		UserVO result = userService.login(vo);
//		
//		if(result == null) {  // 로그인 실패
//			model.addAttribute("msg", "아이디 정보를 확인하세요");
//			return "user/userLogin";
//		}else {
//			// 세션에 회원정보 저장
//			session.setAttribute("userVO", result);
//			return "redirect:/";
//		}
//		
//	}
//	
	@RequestMapping(value = "/login", method = RequestMethod.POST)  
	public ModelAndView login(UserVO vo, Model model,
						HttpSession session) {
		// 로그인 성공시 회원정보를 받아오고, 로그인 실패시 null을 반환
		UserVO result = userService.login(vo);
		
		ModelAndView mv = new ModelAndView(); // view와 model 정보를 동시에 저장하는 객체
		mv.setViewName("/user/userLogin");
		
		if(result != null) {  // 로그인 성공 
			mv.addObject("login", result);
		} else {  // 로그인 실패
			mv.addObject("msg", "아이디, 비밀번호를 확인하세요");
		}
		
		return mv;
	}
	// 로그아웃
	@RequestMapping("/userLogout")
	public String userLogout(HttpSession session) {
		session.invalidate();   // 세션정보 삭제
		return "redirect:/";  // home화면으로 이동
	}
	
	
}
