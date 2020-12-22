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
	<!-- 
    	modal
    	선택자.modal("show");  // open
    	선택자.modal("hide");  // hide
     -->
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
						    <input type="hidden" id="modalRno">   <!-- 수정할 댓글에 rno 번호 -->
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
		//$("#replyModal").modal("show");  // 모달창보이게
		/* $("#modalDelBtn").click(function(){
			$("#replyModal").modal("hide");
		}); */
	
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
			
			// 페이지 넘버 선언
			var pageNum = 1;
			
			// 목록 요청
			getList(1);  // 상세화면 진입시에 리스트 목록을 가져옵니다.
			function getList(pageNum){
				// select 구문에서 필요한 값은 ? bno
				var bno = "${voOne.bno}";
				
				// $.ajax() vs $.getJSON()
				// $.ajax() : get, post, put, delete 공용적으로 처리하는 제이쿼리 함수
				// $.getJSON(요청주소, 콜백함수) : 단순히 get방식의 데이터만 얻어올 때 사용하는 기능.
				$.getJSON(   // json형식의 get방식을 가져올때 사용하는 함수
					"../reply/getList/"+bno+"/"+pageNum, function(data){
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
						strAdd += "<a href='"+data[i].rno+"' class='right replyModify'><span class='glyphicon glyphicon-pencil'></span>수정</a>";
						strAdd += "<a href='"+data[i].rno+"' class='right replyDelete'><span class='glyphicon glyphicon-remove'></span>삭제</a>";
						strAdd += "</div>";
						strAdd += "<p class='clearfix'>"+data[i].reply+"</p>";
						strAdd += "</div>";
						strAdd += "</div>";
					}  //for end
					$("#replyList").html(strAdd);  //replyList자식으로 추가
				});  				
			} // end getList
			
			// 수정 삭제 모달창 핸들러
			// ajax가 비동기실행으로 순서를 보장하지 않기 때문에, 실제 이벤트 선언이 먼저 실행됩니다.
			// 그렇다면, 화면에 댓글 관련 창은 아무것도 없는 형태이므로, 일반클릭 이벤트는 동작하지 않습니다.
			// 이때, 이미 존재하는 태그 replyList(부모)에 이벤트를 등록하고 이벤트를 전파시켜서 사용하는 위임방식을 반드시 써야 합니다.
			$("#replyList").on("click","a",function(){
				event.preventDefault();  //고유 이벤트 중단
				// 1. 수정버튼 ? 삭제버튼? 인지 확인하기
				// - event 객체 또는 jquery this 이용
				// - jquery hasClass()함수를 이용해서
				// - 현재 클릭한 a태그 href안에 있는 rno번호를 -> 모달창에 hidden태그에 옮기기
				//console.log($(this).hasClass("replyModify")); // 있으면 true 없으면 false
				
				// jquery this가 JS의 event.target과 유사
				var rno = $(this).attr("href");
				$("#modalRno").val(rno);	
				
				if($(this).hasClass("replyModify")){   // event.target.classList.contains("replyModify") 와 같은 의미
					// 수정을 눌렀을때는 수정창 형식으로 변경
					$(".modal-title").html("댓글수정");
					$("#modalReply").show();  
					$("#modalModBtn").show(); 
					$("#modalDelBtn").hide();  // 삭제버튼 숨김
					$("#replyModal").modal("show"); 
				}else if($(this).hasClass("replyDelete")){
					// 삭제를 눌렀을때는 삭제창 형식으로 변경
					$(".modal-title").html("댓글삭제");
					$("#modalReply").hide();  // textarea숨김
					$("#modalModBtn").hide();  // 수정버튼 숨김
					$("#modalDelBtn").show();
					$("#replyModal").modal("show"); 
				}
							
			}); // on end
			
			
			// 수정이벤트
			$("#modalModBtn").click(function(){
				/*
					1. 모달창에 rno, reply, replyPw값을 얻습니다
					2. ajax함수를 이용해서 POST방식으로 reply/update 요청. 필요한 값은 JSON형식으로 처리
					3. 서버에서는 요청을 받아서 비밀번호를 확인하고, 비밀번호가 맞다면 업데이트를 진행
					4. 만약 비밀번호가 틀렸다면 0을 반환해서 "비밀번호가 틀렸습니다" 경고창을 띄우세요
					5. 업데이트가 성공적으로 진행되었다면 modal창의 값을 공백으로 초기화시키세요.
				*/
				var rno = $("#modalRno").val();
				var reply = $("#modalReply").val();
				var replyPw = $("#modalPw").val();
				//console.log(rno, reply, replyPw);
				
				if(rno === "" || reply === "" || replyPw === ""){
					alert("내용, 비밀번호를 작성하세요");
					return;
				}
				
				$.ajax({
					type : "POST",
					url : "../reply/update",
					data : JSON.stringify({"rno":rno, "reply":reply,"replyPw":replyPw}),
					contentType : "application/json; charset=utf-8",
					success : function(data){
						if(data === 1){
							alert("수정 성공!");
							$("#modalReply").val(""); // 수정창 비우기
							$("#modalPw").val(""); // 수정창 비우기
							$("replyModal").modal("hide");
							getList(); // 목록호출
						}else{
							alert("비밀번호를 확인하세요");
							$("#modalPw").val("");
						}
					},
					error : function(status, error){
						
					}
				});  //ajax end
				
			});  // 수정이벤트 end
			
			
			// 삭제 이벤트
			$("#modalDelBtn").click(function(){
				/*
					1. 모달창에 rno, replyPw값을 얻습니다.
					2. ajax함수를 이용해서 POST방식으로 reply/delete요청, 필요한 값은 JSON형식으로 처리
					3. 서버에서는 요청을 받아서 비밀번호를 확인하고, 비밀번호가 일치한다면 삭제를 진행하면 됩니다.
					4. 비밀번호가 틀렸다면, 0을 반환해서 "경고창을 띄우면됩니다."
				*/
				
				var rno = $("#modalRno").val();
				var replyPw = $("#modalPw").val();
				
				if(replyPw === ""){
					alert("비밀번호를 작성해주세요");
					return;
				}
				$.ajax({
					type : "POST",
					url : "../reply/delete",
					data : JSON.stringify({"rno" : rno, "replyPw" : replyPw}),
					contentType : "application/json; charset=utf=8",
					success : function(data){
						if(data === 1){
							alert("삭제되었습니다");
							$("#modalPw").val("");
							$("#replyModal").modal("hide");
							getList();
						}else if(data === -1){
							alert("비밀번호를 확인하세요");
							$("#modalPw").val("");
						}else {
							alert("삭제에 실패하였습니다.관리자에게 문의하세요");
							$("#modalPw").val("");
						}
					},
					error : function(error){
						console.log("error : "+error);
					}
				});
			}); // delete end
			
			
			//javascript에서 날짜 포맷팅
			/* function timeStamp(millis){
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
			} // timeStamp end */
			
			// 선생님
			function timeStamp(millis){
				var now = new Date();   // 현재시간
				var gap = now.getTime() - millis;  // 현재시간 밀리초 - 작성일 밀리초
				var time;  // 리턴할 문자열
				
				if(gap < 1000 * 60 * 60 * 24){ // 1일 미만인 경우
					if(gap < 1000 * 60 *60){ // 1시간 미만인 경우
						time = "방금전";		
					}else { // 1시간~ 1일미만
						 time = parseInt(gap/(1000*60*60))+"시간전";
					}
				}else { // 1일이상
					var date = new Date(millis);  // 밀리초기준의 날짜
					var year = date.getFullYear();  // 년
					var month = date.getMonth() + 1;  // 월
					var day = date.getDate();  // 일
					var hour = date.getHour();  // 시
					var minute = date.getMinutes(); // 분
					var second = date.getSeconds();  // 초
					time = year + "년" + month + "월" + day + "일 " + (hour < 10 ? "0"+hour : hour) +":"+(minute<10?"0"+minute:minute)+":"+ (second<10?"0"+second:second);
				}
				
				return time;
			}
		});
	</script>
	
	
	
	
	
	
	
	
	