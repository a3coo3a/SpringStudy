package ex08.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ex02.construct.Chef;
import ex02.construct.Hotel;
import ex03.setter.DatabaseDev;
import ex03.setter.MemberDAO;
import ex04.quiz.Airplane;
import ex04.quiz.Battery1;
import ex04.quiz.Battery2;
import ex04.quiz.Car;

// 이 자바 파일 자체를 xml 대체라고 보는 거야
// 어노테이션(@) : 기능 달린 주석
// @Configuration - 컨테이너
// @Bean - 빈으로 사용하겠다

@Configuration     // 이 클래스가 스프링 설정 파일이라는 것을 표기 - xml 문서를 대체
public class JavaConfig {
	
	// @Bean이 붙은 메서드를 스프링컨테이너가 객체로 생성합니다.
	@Bean      // 이 메서드를 하나의 빈으로 보세요
	public Chef chef() {
		return new Chef();
	}

	@Bean
	public Hotel hotel() {
		return new Hotel(chef());
	}
	
	//---------------------------------------------------------------
	
//	<!-- dao, databaseDev -->
//	<bean id="db" class="ex03.setter.DatabaseDev">
//		<property name="url" value="bean으로넣은데이터베이스주소" />
//		<property name="uid" value="bean으로넣은데이터베이스아이디" />
//		<property name="upw" value="bean으로넣은데이터베이스비번" />
//	</bean>
	
	
	@Bean
	public DatabaseDev db() {
		DatabaseDev dev = new DatabaseDev();
		dev.setUrl("자바로 설정한 데이터베이스 주소");
		dev.setUid("자바로 설정한 데이터베이스 아이디");
		dev.setUpw("자바로 설정한 데이터베이스 비밀번호");
		
		return dev;
	}
	
	
//	<bean id="memberDAO" class="ex03.setter.MemberDAO">
//		<property name="ds" ref="db" />
//	</bean>
	
	
	@Bean
	public MemberDAO memberDAO() {
		MemberDAO dao = new MemberDAO();
		dao.setDs(db());
		
		return dao;
	}
	
	
	//------------------------------------------------------------------
//	<bean id="ba1" class="ex04.quiz.Battery1"/>
//	<bean id="car" class="ex04.quiz.Car">
//		<constructor-arg ref="ba1"/>
//	</bean>
	
	@Bean
	public Battery1 ba1() {
		return new Battery1();
	}
	@Bean
	public Car car() {
		return new Car(ba1());
	}
	
//	<bean id="ba2" class="ex04.quiz.Battery2" />
//	<bean id="airplane" class="ex04.quiz.Airplane">
//		<property name="battery" ref="ba2" />
//	</bean>
	
	@Bean
	public Battery2 ba2() {
		return new Battery2();
	}
	@Bean
	public Airplane airplane() {
		Airplane ap = new Airplane();
		ap.setBattery(ba2());
		return ap;
	}
	
}
