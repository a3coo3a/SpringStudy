package com.team404.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team404.command.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping("/userJoin")
	public String userJoin() {
		return "user/userJoin";
	}
	
	@RequestMapping("/userLogin")
	public String userLogin() {
		return "user/userLogin";
	}
	
	
	// 일반 Controller에서는 비동기 요청 메서드는 ResponseBody를 붙여야한다.
	@ResponseBody   // 응답요청을 viewResolve로 반환하는 것이 아닌, 요청이 들어오는 곳으로 Resonse Header정보를 만들어서 보내준다.
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public int idCheck(@RequestBody UserVO vo) {
		System.out.println(vo.toString());
		return 1;
	}
}
