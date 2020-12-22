package com.team404.reply.service;

import java.util.ArrayList;

import com.team404.command.ReplyVO;

public interface ReplyService {
	
	public int replyRegist(ReplyVO vo);
	public ArrayList<ReplyVO> getList(int bno);
	public int update(ReplyVO vo);
	public int pwCheck(ReplyVO vo);
	public int delete(ReplyVO vo);
}
