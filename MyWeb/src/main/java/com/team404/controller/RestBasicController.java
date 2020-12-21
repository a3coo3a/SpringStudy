package com.team404.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.http.converter.feed.AtomFeedHttpMessageConverter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team404.command.FreeBoardVO;

// @ResponseBody + @RequestBody를 합쳐놓은것
@RestController
public class RestBasicController {
	/*
	 * 크롬 확장프로그램에 
	 * Yet Another REST Client 추가
	 * */
	
	// 1. RestController는 기본적으로 retrun값이 리졸버 뷰로 전달되는게 아니라, 요청된 주소로 반환 됩니다.
	// produces는 타입을 지정해주면, 해당 타입으로 사용자에게 응답하겠다는 뜻, 기본형이 json형식
	@GetMapping(value = "/getText", produces = "text/plain; charset=utf-8")   // @RequestMapping의 method = get방식
	public String getText() {
		System.out.println("REST API 실행됨");
		return "안녕하세요";
	}
	
	// 객체 반환
	// jackson-data-bind가 반드시 필요
	@GetMapping(value = "/getObject", produces = "application/json; charset=UTF-8")
	public FreeBoardVO getObject() {
		return new FreeBoardVO(10, "헬로", "테스트", "테스트", null, null);
	}
	
	// 값을 받고 객체형태로 반환
	// get방식으로 받을 것 
	@GetMapping(value = "/getCollection")
	public ArrayList<FreeBoardVO> getCollection(@RequestParam("key") String key,@RequestParam("bno") int bno){
		System.out.println("브라우저에서 넘어온 값 : "+key);
		System.out.println("브라우저에서 넘어온 값 : "+bno);
		ArrayList<FreeBoardVO> list = new ArrayList<>();
		for(int i = 1; i <= 10; i++) {
			FreeBoardVO vo = new FreeBoardVO();
			vo.setBno(i);
			vo.setTitle("테스트"+i);
			vo.setContent("테스트"+i);
			
			list.add(vo);
		}
		
		return list;
	}
	
	// 값/값/값의 url형태로 받고 Map을 반환
	@GetMapping("/getPath/{sort}/{rank}/{page}")
	//http://localhost:13002/myweb/getPath/desc/23/ddd
	public HashMap<String, Object> getPath(@PathVariable("sort") String sort,
										   @PathVariable("rank") String rank,
										   @PathVariable("page") String page){
		System.out.println(sort);
		System.out.println(rank);
		System.out.println(page);
		
		//... 처리
		HashMap<String, Object> map = new HashMap<>();
		FreeBoardVO vo = new FreeBoardVO(1, "테스트", "테스트", "테스트", null, null);
		map.put("info", vo);
		
		return map;
	}
	
	// Post 형식의 JSON형식으로 값을 받음, 객체로 반환
	// 1. 화면에서 JSON형식으로 넘어오는 데이터를 @RequestBody어노테이션으로 맵핑
	// 2. 화면에서 데이터 보낼때 content-type을 선언해서 데이터의 유형을 알려줘야 합니다.
	
	// 크로스 도메인 정책 : 서버가 다른경우, 스프링은 기본적으로 요청을 받아주지 않는데,
	//					이런요청을 허용해주는 옵션  (CroosOrigin)
	
	//@CrossOrigin(origins = "*")   // 어떤것이든 다른 서버여도 다 받겠다는 의미
	@CrossOrigin(origins = "http://127.0.0.1:5502") // 이주소의 이 포트 번호만 들어오는걸 허용하겠다.  
	@PostMapping("/getJson")        //post형식만을 허용
	public ArrayList<FreeBoardVO> getJson(@RequestBody FreeBoardVO vo){
		System.out.println(vo.toString());
		
		//...처리
		ArrayList<FreeBoardVO> list = new ArrayList<>();
		FreeBoardVO fvo = new FreeBoardVO();
		fvo.setTitle("결과입니다");
		list.add(fvo);
		
		return list;
	}
	
	
	
	
	
	
	
}
