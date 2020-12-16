package com.team404.freeboard.service;

import java.util.ArrayList;

import com.team404.command.FreeBoardVO;


public interface FreeBoardService {
	// 추상메서드 선언
	public void regist(FreeBoardVO vo);
	public ArrayList<FreeBoardVO> getList();
	public FreeBoardVO getContent(int bno);
	public int update(FreeBoardVO vo);
	public int delete(int bno);
}
