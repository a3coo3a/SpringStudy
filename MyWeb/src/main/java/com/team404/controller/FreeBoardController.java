package com.team404.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team404.command.FreeBoardVO;
import com.team404.freeboard.service.FreeBoardService;

@Controller
@RequestMapping("/freeBoard")
public class FreeBoardController {
	
	@Autowired
	@Qualifier("freeBoardService")
	private FreeBoardService freeBoardService;
	
	// 글목록
	@RequestMapping("/freeList")
	public String freeList(Model model) {
		// 화면으로 넘어갈때 글정보를 가지고 갈 수 있도록 처리
		ArrayList<FreeBoardVO> list = new ArrayList<>();
		list = freeBoardService.getList();
		model.addAttribute("list", list);
		return "freeBoard/freeList";
	}
	
	// 글등록화면
	@RequestMapping("/freeRegist")
	public String freeRegist() {
		return "freeBoard/freeRegist";
	}
	
	// 글등록
	@RequestMapping(value = "/registForm", method =  RequestMethod.POST)
	public String registForm(FreeBoardVO vo, RedirectAttributes RA) {
		freeBoardService.regist(vo);
		RA.addFlashAttribute("msg", "정상적으로 등록 처리 되었습니다.");  // 메세지를 리스트화면으로 전달
		
		//return "freeBoard/freeList";   // 이렇게 하면 주소값이 : http://localhost:8282/myweb/freeBoard/registForm 나옴
		return "redirect:/freeBoard/freeList";
	}
	
//	// 글상세
//	@RequestMapping(value = "/freeDetail", method = RequestMethod.GET)
//	public String freeDetail(@RequestParam("bno") int bno, Model model) {
//		FreeBoardVO vo = freeBoardService.getContent(bno);
//		// 화면으로 넘어갈때  bno기반의 데이터를 가지고 상세화면으로 가도록  getContent()로 처리
//		model.addAttribute("voOne", vo);
//		return "freeBoard/freeDetail";
//	}
//	
//	// 글 변경 화면
//	@RequestMapping(value = "/freeModify", method = RequestMethod.GET)
//	public String freeModify(@RequestParam("bno") int bno, Model model) {
//		FreeBoardVO vo = freeBoardService.getContent(bno);
//		model.addAttribute("voOne", vo);
//		return "freeBoard/freeModify";
//	}
	
	// 상세화면과 변경화면의 기능이 같아서 하나로 묶어서 사용
	@RequestMapping(value = {"/freeModify","/freeDetail"}, method = RequestMethod.GET)
	public void freeModify(@RequestParam("bno") int bno, Model model) {
		FreeBoardVO vo = freeBoardService.getContent(bno);
		model.addAttribute("voOne", vo);
	}
	
	@RequestMapping(value = "/freeUpdate", method = RequestMethod.POST)
	public String freeUpdate(FreeBoardVO vo, RedirectAttributes RA) {
		int result = freeBoardService.update(vo);
		if(result == 1) {
			RA.addFlashAttribute("msg", "게시글이 수정되었습니다.");
		}else {
			RA.addFlashAttribute("msg", "수정에 실패하였습니다.");
		}
		return "redirect:/freeBoard/freeList";
	}
	
	@RequestMapping("/freeDelete")
	public String freeDelete(@RequestParam("bno") int bno, RedirectAttributes RA) {
		int result = freeBoardService.delete(bno);
		if(result == 1) {
			RA.addFlashAttribute("msg", bno + "번 게시글이 삭제되었습니다");
		}else {
			RA.addFlashAttribute("msg", "삭제에 실패했습니다");
		}
		return "redirect:/freeBoard/freeList";
	}
}
