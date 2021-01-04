# 01 스프링 소개

## 1. 스프링 주요 기능

### Dependency Injection

> 스프링 프레임워크의 핵심 기능

### transaction management

> 트랜잭션을 관리해주는 것

---

## 2. 기업형 응용 프로그램을 보조하기 위한 쉬운 프레임워크

### Java EE

- 분산형, 기업형 응용 프로그램 개발을 위한 API
- 결합력을 낮추는 DI, DB Transaction처리, 로그 처리....

### Java SE

- 일반적인 로컬 응용 프로그램 개발을 위한 API
- 파일 I/O, 콘솔 I/O, 윈도우 I/O, 네트워크 I/O, Thread, ....

> Java EE를 대신해서 Spring이 사용된다고 생각하면 됨.

> Java SE + Spring 을 사용하여 웹개발

Java EE와 함께 사용하는 경우도 있음.

Java EE의 대부분의 기능을 Spring으로 사용가능.

---

## 3. 웹을 위한 스프링 프레임워크 모듈

### MVC ← DI를 활용

&nbsp;&nbsp;느슨한 결합력과 인터페이스

### 트랜잭션 ← AOP을 활용

### 인증과 권한 ← Servlet Filter를 활용
