package com.team404.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team404.command.ReplyVO;
import com.team404.common.util.Criteria;
import com.team404.reply.service.ReplyService;

@RestController
@RequestMapping("/reply")
public class ReplyController {
	
	@Autowired
	@Qualifier("replyService")
	private ReplyService replyService;

	// 등록요청
	@PostMapping("/replyRegist")
	public int replyRegist(@RequestBody ReplyVO vo) {
		//System.out.println(vo.toString());
		int result = replyService.replyRegist(vo);  // 성공 1, 실패 0
		
		return result;  // 레스트 컨트롤러 return 결과는 호출 구문으로 갑니다(ResponseBody의 역할)
	}
	
	// 목록요청
	@GetMapping("/getList/{bno}/{pageNum}")
	public HashMap<String, Object> getList(@PathVariable("bno") int bno, @PathVariable("pageNum") int pageNum){
		// 1. 화면에서 더보기 버튼을 생성하고, 처음 실행될때 pageNum = 1과 해당 bno 번호를 보내줌.
		// 2. getList(bno, cri)를 받습니다.
		// 3. 마이바티스에 매개변수가 2개 전달되는 경우는 @Param 어노테이션을 사용해야함.
		// 4. 페이지 쿼리 구문을 실행
		// 5. 이 게시글에 대한 토탈 게시글 수
		
		Criteria cri = new Criteria(pageNum, 20); // 20개 데이터
		ArrayList<ReplyVO> list = replyService.getList(bno, cri);
		
		// 게시글에 대한 total
		int total = replyService.getTotal(bno);
		// 맵에 키, value로 저장해서 반환
		HashMap<String, Object> map = new HashMap<>();
		map.put("list",list);
		map.put("total",total);
		
		//System.out.println(list.toString());
		return map;
	}
	
	//수정요청
	@PostMapping("/update")
	public int update(@RequestBody ReplyVO vo) {
		int result = replyService.update(vo);
		System.out.println("성공여부? : "+result);
		//System.out.println(vo.toString());
		return result;
	}
	
	// 삭제요청
	@PostMapping("/delete")
	public int delete(@RequestBody ReplyVO vo) {
		//System.out.println(vo.toString());
		int result = replyService.pwCheck(vo);
		if(result == 1) { //비밀번호가 맞은 경우
			return replyService.delete(vo);
		}else { // 비밀번호가 틀린경우
			return -1;
		}
	}
}
