package com.simple.service;

import java.util.ArrayList;

import com.simple.command.ScoreVO;

public interface ScoreService {
	// 서비스 클래스에서 구현할 메서드를 추상메서드로 선언
	public void scoreRegist(ScoreVO vo);  // 등록메서드
	public ArrayList<ScoreVO> getList();  // 목록메서드
	public void scoreDelete(int index);
}
