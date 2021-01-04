<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <section>
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-7 col-xs-10 login-form">
                    <div class="titlebox">
                        로그인
                    </div>
                    <div><h3 align="center">${msg }</h3></div>
                    <form action="loginForm" method="post" id="loginForm">
                        <div class="form-group"><!--사용자클래스선언-->
                            <label for="id">아이디</label>
                            <input type="text" class="form-control" id="id" name="userId" placeholder="아이디">
                         </div>
                         <div class="form-group"><!--사용자클래스선언-->
                            <label for="id">비밀번호</label>
                            <input type="password" class="form-control" id="pw" name="userPw" placeholder="비밀번호">
                         </div>
                         <div class="form-group">
                            <button type="button" class="btn btn-info btn-block" id="loginBtn">로그인</button>
                            <button type="button" class="btn btn-primary btn-block" onclick="location.href='userJoin'">회원가입</button>
                         </div>
                    </form>                
                </div>
            </div>
        </div>
    </section>
    
	<script>
		$("#loginBtn").click(function(){
			var userId = $("#id").val();
			var userPw = $("#pw").val();
			
			$.ajax({
				type:"POST",
				url :"loginCheck",
				data : JSON.stringify({"userId":userId, "userPw":userPw}),
				contentType : "application/json; charset=utf-8",
				success: function(data){
					if(data.userId !== userId){
						alert("가입되어 있지 않은 아이디 입니다.");
					}else if(data.userPw !== userPw){
						alert("비밀번호가 다릅니다.");
					}else if(data.userId === userId && data.userPw === userPw){
						$("#loginForm").submit();
					}
				},
				error: function(status, error){
					alert("로그인에 문제가 생겼습니다. 관리자에게 문의하세요");
				}
			});
		})
	</script>