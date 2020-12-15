package com.simple.mapper;

import java.util.ArrayList;

import com.simple.command.ScoreVO;

// mapper는 영속성있는 계속 유지되는 파일
// 그래서, 별도로 두고 실제 구현체는 resources에 구현
public interface ScoreMapper {
	public void scoreRegist(ScoreVO vo);  // 등록메서드
	public ArrayList<ScoreVO> getList();  // 목록메서드
	public void scoreDelete(int index);
}
