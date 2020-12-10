package ex08.javaconfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ex02.construct.Hotel;
import ex03.setter.MemberDAO;
import ex04.quiz.Airplane;
import ex04.quiz.Car;

public class MainClass {

	public static void main(String[] args) {

		// 자바 설정을 확인하는 클래스
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);
		
		Hotel h = ctx.getBean(Hotel.class);
		Hotel h2 = ctx.getBean(Hotel.class);
		System.out.println(h == h2);
		h.getChef().cooking();
		
		MemberDAO dao = ctx.getBean(MemberDAO.class);
		dao.info();
		
		// battery와 car, airplane을 자바생성으로 변경
		Car car = ctx.getBean(Car.class);
		car.getBattery().energy();
		
		Airplane ap = ctx.getBean(Airplane.class);
		ap.getBattery().energy();
	}

}
