package ex07.quiz;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Computer {
	// 키보드, 마우스, 모니터에 대한 멤버변수를 만들고, 
	// resource, autowired를 사용해서 자동주입.
	// main에서 computer의 info기능을 확인
	
	@Resource
	private Keyboard keyboard;
	@Autowired
	@Qualifier("monitor")
	private Monitor monitor;
	@Resource(name="mouse")
	private Mouse mouse;
	
	public void computerInfo() {
		System.out.println("나 컴퓨터");
		keyboard.info();
		monitor.info();
		mouse.info();
	}
	
	public Computer() {}

	public Computer(Keyboard keyboard, Monitor monitor, Mouse mouse) {
		super();
		this.keyboard = keyboard;
		this.monitor = monitor;
		this.mouse = mouse;
	}

	public Keyboard getKeyboard() {
		return keyboard;
	}

	public void setKeyboard(Keyboard keyboard) {
		this.keyboard = keyboard;
	}

	public Monitor getMonitor() {
		return monitor;
	}

	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}

	public Mouse getMouse() {
		return mouse;
	}

	public void setMouse(Mouse mouse) {
		this.mouse = mouse;
	}
	
	
	
}
