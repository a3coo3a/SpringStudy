package com.team404.controller;

import java.util.ArrayList;

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
	@GetMapping("/getList/{bno}")
	public ArrayList<ReplyVO> getList(@PathVariable("bno") int bno){
		ArrayList<ReplyVO> list = replyService.getList(bno);
		//System.out.println(list.toString());
		return list;
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
