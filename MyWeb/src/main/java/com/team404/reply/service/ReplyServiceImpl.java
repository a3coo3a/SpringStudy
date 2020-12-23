package com.team404.reply.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team404.command.ReplyVO;
import com.team404.common.util.Criteria;
import com.team404.reply.mapper.ReplyMapper;

@Service("replyService")
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper replymapper;
	
	@Override
	public int replyRegist(ReplyVO vo) {
		return replymapper.replyRegist(vo);
	}

	/*
	 * @Override public ArrayList<ReplyVO> getList(int bno) { return
	 * replymapper.getList(bno); }
	 */

	@Override
	public ArrayList<ReplyVO> getList(int bno, Criteria cri) {
		return replymapper.getList(bno, cri);
	}
	
	@Override
	public int getTotal(int bno) {
		return replymapper.getTotal(bno);
	}

	@Override
	public int update(ReplyVO vo) {
		// 1. sql문을 두번에 실행 (select검증, 업데이트)
		// 2. sql문을 한번에 실행
		
		return replymapper.update(vo);
	}

	@Override
	public int pwCheck(ReplyVO vo) {
		return replymapper.pwCheck(vo);
	}
	
	@Override
	public int delete(ReplyVO vo) {
		return replymapper.delete(vo);
	}




}
