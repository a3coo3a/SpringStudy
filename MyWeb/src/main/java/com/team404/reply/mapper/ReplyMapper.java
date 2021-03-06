package com.team404.reply.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.team404.command.ReplyVO;
import com.team404.common.util.Criteria;

public interface ReplyMapper {

	public int replyRegist(ReplyVO vo);
	//public ArrayList<ReplyVO> getList(int bno);
	// 마이바티스에서 2개이상의 파라미터를 받을 때 @Param으로 이름을 지정해야 합니다.
	public ArrayList<ReplyVO> getList(@Param("bno") int bno, @Param("cri") Criteria cri);
	public int getTotal(int bno);
	public int update(ReplyVO vo);
	public int pwCheck(ReplyVO vo);
	public int delete(ReplyVO vo);
}
