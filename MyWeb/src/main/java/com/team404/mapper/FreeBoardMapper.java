package com.team404.mapper;

import java.util.ArrayList;
import com.team404.command.FreeBoardVO;

public interface FreeBoardMapper {
	public void freeboardRegist(FreeBoardVO vo); //board 등록 메서드
	public ArrayList<FreeBoardVO> getList(); //모든 게시물 가져오기
	public void freeboardDelete(int num); //게시글 삭제 메서드
}
