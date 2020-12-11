package com.simple.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.simple.command.ScoreVO;
import com.simple.dao.ScoreDAO;

@Service("scoreService")   // scroeService이름으로 bean을 생성하시오
public class ScoreServiceImpl implements ScoreService {
	@Autowired
	@Qualifier("scoreDAO")
	private ScoreDAO scoreDAO;
	
	@Override
	public void scoreRegist(ScoreVO vo) {
		scoreDAO.scoreRegist(vo);
	}

	@Override
	public ArrayList<ScoreVO> getList() {
		return scoreDAO.getList();
	}

	@Override
	public void scoreDelete(int index) {
		scoreDAO.scoreDelete(index);
	}

}
