package com.simple.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.simple.command.ScoreVO;

@Repository("scoreDAO")      //db영역이라는 의미
public class ScoreDAOImpl implements ScoreDAO {

	// DB라고 가정
	private ArrayList<ScoreVO> list = new ArrayList<>();
	
	// DB관련된 작업
	@Override
	public void scoreRegist(ScoreVO vo) {
		System.out.println(vo.toString());
		list.add(vo);
	}

	@Override
	public ArrayList<ScoreVO> getList() {
		return list;
	}

}