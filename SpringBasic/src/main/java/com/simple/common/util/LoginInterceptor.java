package com.simple.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	// 1. 인터셉터 클래스는 스프링에서 제공되는 HandlerInterceptor 클래스를 상속받습니다.
	// 2. 3가지 메서드를 오버라이딩해서 사용합니다.
	
	// preHandler는 컨트롤러를 실행하기 전에 요청을 가로챕니다.
	// 일반적으로 로그인, 세션처리에 이용됩니다.
	// 세션처리를 했다면 스프링설정파일에 빈으로 등록, <mvc:interceptor> 태그를 사용합니다.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		System.out.println("로그인 인터셉터 실행됨.");
		
		// 세션검사
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		if(user_id == null) { // 인증값이 없음. 즉, 로그인되지 않음
			response.sendRedirect(request.getContextPath()+"/session/login");  // redirect가 컨트롤러와 같이 두번사용되면 안됩니다.
			// 여기서 사용하면 컨트롤러꺼는 없애 줘야함.
			return false;
		}else { // 로그인된 유저
			return true;   // 핸들러메서드 실행후에 컨트롤러를 수행해라는 의미의 true // false는 반대
		}
		
	}

	
	// postHandle는 컨트롤러의 요청을 실행시킨 후에 요청을 가로챕니다.
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	//viewRewolver 처리후에 화면으로 가기전에 요청을 가로챕니다.
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
	
	
	
}
