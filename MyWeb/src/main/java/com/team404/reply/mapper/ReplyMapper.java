package com.team404.reply.mapper;

import java.util.ArrayList;

import com.team404.command.ReplyVO;

public interface ReplyMapper {

	public int replyRegist(ReplyVO vo);
	public ArrayList<ReplyVO> getList(int bno);
	public int update(ReplyVO vo);
	public int pwCheck(ReplyVO vo);
	public int delete(ReplyVO vo);
}
