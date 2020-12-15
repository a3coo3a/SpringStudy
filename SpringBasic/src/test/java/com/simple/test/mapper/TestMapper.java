package com.simple.test.mapper;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.simple.command.BoardVO;

//import com.simple.command.BoardVO;

public interface TestMapper {
	// 인터페이스이름과 동일한 이름의 구현 xml파일을 생성
	public String getTime();	// 조회
	
	//public int insert(BoardVO vo);	// 추가
	public int insert(Map<String, String> map);	// 추가
	
	// 다수의 조회
	public ArrayList<BoardVO> getList();
	public BoardVO getListOne (int num);  // pk를 통한 단일 조회
	
	// 2개의 파라미터를 전달해야 하는 경유
	// 원래 마이비스트는 2개의 파라미터를 허용하지 않는다.
	//public ArrayList<BoardVO> getListTwo(String name, String title);  // 에러
	public ArrayList<BoardVO> getListTwo(@Param("xxx") String name, @Param("yyy") String title);
	
	// quiz
	public int update(BoardVO vo);
	
	// 삭제 quiz
	public int delete(int num);
	
}
