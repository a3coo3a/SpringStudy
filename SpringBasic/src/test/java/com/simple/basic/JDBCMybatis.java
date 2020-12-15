package com.simple.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.simple.command.BoardVO;
import com.simple.test.mapper.TestMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/config/root-context.xml")
public class JDBCMybatis {
	
	// 1. 세션팩토리빈 주입 확인
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactory;

	// 2. TestMapper인터페이스 주입
	// 이 타입, 인터페이스 이름, xml이름 모두가 같아야 연결됨.
	@Autowired
	private TestMapper testMapper;
	
	//마이바티스 객체 확인
//	@Test
//	public void test() {
//		System.out.println("sql확인:"+sqlSessionFactory);
//	}
	
	@Test
	public void getTime() {
		System.out.println(testMapper.getTime());
	}
	
	// insert 구문의 값을 전달할때는 단일값 or VO를 통해서 전달 or Map으로 전달
	// vo를 통한 전달구문
//	@Test
//	public void insertTest() {
//		for(int i = 1; i <=10;i++) {
//			BoardVO vo = new BoardVO();
//			vo.setName("홍길동"+i);
//			vo.setTitle("테스트"+i);
//			vo.setContent("테스트내용"+i);
//			testMapper.insert(vo);
//		}
//	}
	
	// map을 통한 전달구문
//	@Test
//	public void insetTest() {
//		Map<String,String> map = new HashMap<>();
//		map.put("name", "홍길자");
//		map.put("title","맵을통한추가");
//		map.put("content","맵을통한추가내용");
//		testMapper.insert(map);
//	}
	
	@Test
	public void selectTest() {
		ArrayList<BoardVO> list = testMapper.getList();
		System.out.println("반환 결과 : " + list.toString());
	}
	// 10번 게시글 단일 조회
	@Test
	public void selectTen() {
		BoardVO listOne = testMapper.getListOne(10);
		System.out.println("단일조회 결과 : " + listOne.toString());
	}
	
	// 2개의 파라미터를 전달할 경우
	@Test
	public void getListTwo() {
		ArrayList<BoardVO> list = testMapper.getListTwo("홍길동", "테스트6");
		System.out.println(list.toString());
	}
	
	// 게시글 수정 테이스
	// update태그를 이용합니다.
	// vo객체를 이요해서 수정내용(글제목, 글내용)을 입력하고
	// 10번 게시글을 수정해보세요
	@Test
	public void updateTest() {
		BoardVO vo = new BoardVO();
		vo.setNum(10);
		vo.setTitle("글제목수정");
		vo.setContent("글내용수정");
		int result = testMapper.update(vo);
		System.out.println("업데이트 성공 여부 : " + result);
	}
	
	// 게시글 삭제 테스트
	// delete태그를 이용
	// 3번 게시글을 삭제하는 구문 작성(삭제여부도 확인)
	@Test
	public void deleteTest() {
		int result = testMapper.delete(3);
		System.out.println("삭제 성공여부 : " + result);
	}
}
