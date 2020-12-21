<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <section>
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-md-9 write-wrap">
                        <div class="titlebox">
                            <p>상세보기</p>
                        </div>
                        
                        <form>
                            <div>
                                <label>DATE</label>
                                <p><fmt:formatDate value="${voOne.regdate}" pattern="yyyy-MM-dd"/></p>
                            </div>   
                            <div class="form-group">
                                <label>번호</label>
                                <input class="form-control" name='##' value="${voOne.bno }" readonly>
                            </div>
                            <div class="form-group">
                                <label>작성자</label>
                                <input class="form-control" name='##' value="${voOne.writer }" readonly>
                            </div>    
                            <div class="form-group">
                                <label>제목</label>
                                <input class="form-control" name='##' value="${voOne.title }" readonly>
                            </div>

                            <div class="form-group">
                                <label>내용</label>
                                <textarea class="form-control" rows="10" name='##' readonly>${voOne.content }</textarea>
                            </div>

                            <button type="button" class="btn btn-primary" onclick="location.href='freeModify?bno=${voOne.bno}'">변경</button>
                            <button type="button" class="btn btn-dark" onclick="location.href='freeList'">목록</button>
                    </form>
                </div>
            </div>
        </div>
        </section>
        
        <section style="margin-top: 80px;">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-md-9 write-wrap">
                        <form class="reply-wrap">
                            <div class="reply-image">
                                <img src="../resources/img/profile.png">
                            </div>
                            <!--form-control은 부트스트랩의 클래스입니다-->
	                    <div class="reply-content">
	                        <textarea class="form-control" rows="3" id="reply"></textarea>
	                        <div class="reply-group">
	                              <div class="reply-input">
	                              <input type="text" class="form-control" placeholder="이름" id="replyId">
	                              <input type="password" class="form-control" placeholder="비밀번호" id="replyPw">
	                              </div>
	                              
	                              <button type="button" class="right btn btn-info" id="replyRegist">등록하기</button>
	                              
	                        </div>
	
	                    </div>
                        </form>

                        <!--여기에접근 반복-->
                        <div id="replyList">
                        
                        <!-- <div class='reply-wrap'>
                            <div class='reply-image'>
                                <img src='../resources/img/profile.png'>
                            </div>
                            <div class='reply-content'>
                                <div class='reply-group'>
                                    <strong class='left'>honggildong</strong> 
                                    <small class='left'>2019/12/10</small>
                                    <a href='#' class='right'><span class='glyphicon glyphicon-pencil'></span>수정</a>
                                    <a href='#' class='right'><span class='glyphicon glyphicon-remove'></span>삭제</a>
                                </div>
                                <p class='clearfix'>여기는 댓글영역</p>
                            </div>
                        </div> -->
                        
                        </div>
                    </div>
                </div>
            </div>
        </section>
        
	<!-- 모달 -->
	<div class="modal fade" id="replyModal" role="dialog">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="btn btn-default pull-right" data-dismiss="modal">닫기</button>
					<h4 class="modal-title">댓글수정</h4>
				</div>
				<div class="modal-body">
					<!-- 수정폼 id값을 확인하세요-->
					<div class="reply-content">
					<textarea class="form-control" rows="4" id="modalReply" placeholder="내용입력"></textarea>
					<div class="reply-group">
						<div class="reply-input">
						    <input type="hidden" id="modalRno">
							<input type="password" class="form-control" placeholder="비밀번호" id="modalPw">
						</div>
						<button class="right btn btn-info" id="modalModBtn">수정하기</button>
						<button class="right btn btn-info" id="modalDelBtn">삭제하기</button>
					</div>
					</div>
					<!-- 수정폼끝 -->
				</div>
			</div>
		</div>
	</div>

	
	
	<script>
		// 1. 제이쿼리 라이브러리 확인
		// 2. 로딩이 끝난 직후 ready()함수 안에 작성
		$(document).ready(function(){
			$("#replyRegist").click(add);  // click이벤트 
			// 등록함수
			function add(){
				var bno = "${voOne.bno}";   //문자열의 형태로 화면에서 넘어온 bno 번호
				var reply = $("#reply").val();
				var replyId = $("#replyId").val();
				var replyPw = $("#replyPw").val();
				
				if(reply === '' || replyId === '' || replyPw === ''){
					alert("이름, 비밀번호, 내용을 입력하세요");
					return;  // 함수종료
				}
				
				$.ajax({
					type : "POST",
					url : "../reply/replyRegist",  // 요청주소
					data : JSON.stringify({"bno":bno,"reply":reply,"replyId":replyId,"replyPw":replyPw}),
					contentType : "application/json; charset=utf-8",
					success : function(data){
						if(data == 1){
							$("#reply").val("");
							$("#replyId").val("");
							$("#replyPw").val("");
							alert("댓글등록에 성공하였습니다");
							getList();
						}else{
							alert("등록에 실패하였습니다. 잠시후에 시도하세요");
						}
						//console.log("성공여부 : "+data);
					},
					error: function(error){
						alert(error,"등록실패입니다. 관리자에게 문의하세요");
					}
				});
			}
			
			// 목록 요청
			getList();  // 상세화면 진입시에 리스트 목록을 가져옵니다.
			function getList(){
				// select 구문에서 필요한 값은 ? bno
				var bno = "${voOne.bno}";
				
				// $.ajax() vs $.getJSON()
				// $.ajax() : get, post, put, delete 공용적으로 처리하는 제이쿼리 함수
				// $.getJSON(요청주소, 콜백함수) : 단순히 get방식의 데이터만 얻어올 때 사용하는 기능.
				$.getJSON(   // json형식의 get방식을 가져올때 사용하는 함수
					"../reply/getList/"+bno, function(data){
					if(data.length <= 0){  // 댓글이 없는 경우 함수 종료
						return;  // 함수종료
					}
					
					var strAdd = ""; // 화면에 그려넣을 태그를 문자열의 형태로 추가
					for(var i = 0; i < data.length; i++){
						strAdd += "<div class='reply-wrap'>";
						strAdd += "<div class='reply-image'>";
						strAdd += "<img src='../resources/img/profile.png'>";
						strAdd += "</div>";
						strAdd += "<div class='reply-content'>";
						strAdd += "<div class='reply-group'>";
						strAdd += "<strong class='left'>"+data[i].replyId+"</strong>";
						strAdd += "<small class='left'>"+timeStamp(data[i].replydate)+"</small>";
						strAdd += "<a href='#' class='right'><span class='glyphicon glyphicon-pencil'></span>수정</a>";
						strAdd += "<a href='#' class='right'><span class='glyphicon glyphicon-remove'></span>삭제</a>";
						strAdd += "</div>";
						strAdd += "<p class='clearfix'>"+data[i].reply+"</p>";
						strAdd += "</div>";
						strAdd += "</div>";
					}  //for end
					$("#replyList").html(strAdd);  //replyList자식으로 추가
				});  				
			} // end getList
			
			//javascript에서 날짜 포맷팅
			function timeStamp(millis){
				// 1시간 기준으로 방금전 or xx시간 or 1일 기준으로 날짜 출력
				var today = new Date();
				var replyTime = new Date(millis);
				
				var betweenTime = Math.floor((today.getTime()-replyTime.getTime())/1000/60);  //분
				if(betweenTime < 1) return "방금전";
				if(betweenTime < 60) return betweenTime+"분전";
				
				var betweenTimeHour = Math.floor(betweenTime/60);   //시간
				if(betweenTimeHour < 24) return betweenTimeHour+"시간전";
				
				var betweenTimeDay = Math.floor(betweenTime/60/24);  //일
				if(betweenTimeDay < 365) return betweenTimeDay+"일전";
				
				var betweenTimeYear = Math.floor(betweenTime/60/24/365);  // 년
				return betweenTimeYear + "년전";
			}
		});
	</script>
	
	
	
	
	
	
	
	
	