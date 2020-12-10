<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>리스트 화면</h2>
	<h3>점수 결과 확인</h3>
	<c:forEach var="list" items="${list }">
		num : ${list.num }<br/>
		name : ${list.name }<br/>
		kor : ${list.kor }<br/>
		eng : ${list.eng }<br/>
		math : ${list.math }<br/>
		<hr/>
	</c:forEach>
	
	
</body>
</html>