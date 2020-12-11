package com.simple.basic;

import java.sql.Connection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zaxxer.hikari.HikariDataSource;

// 스프링 단위 테스트
// pom.xml에서 junit 4.12이상이 있어야 하며, Spring-test가 있어야 합니다.
// run as -> junit test 실행
// 이곳에서 실행이 되면 다른곳에 가도 실행이 된다는 이야기


@RunWith(SpringJUnit4ClassRunner.class) // 스프링프레임워크에서 독립적인 테스트환경 제공
@ContextConfiguration("file:src/main/webapp/WEB-INF/config/root-context.xml")
public class JDBCBasic {
	
	@Autowired
	private HikariDataSource ds;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test // 해당메서드만 실행
	public void test()  {
		System.out.println("커넥션 풀 객체 : " + ds);
		
		Connection conn;
		
		try {
			conn = (Connection) ds.getConnection();
			System.out.println(conn);  //JDBC코드작성이 가능
			System.out.println("스프링템플릿 : "+jdbcTemplate);  // 스프링템플릿 객체 확인
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
