<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <section>
        <div class="container-fluid">
            <div class="row">
                <!--lg에서 9그리드, xs에서 전체그리드-->   
                <div class="col-lg-9 col-xs-12 board-table">
                    <div class="titlebox">
                        <p>자유게시판</p>
                    </div>
                    <hr>
                    
                    <!--form select를 가져온다 -->
                    <form action="freeList">
			    		<div class="search-wrap">
	                       <button type="submit" class="btn btn-info search-btn">검색</button>
	                       <input type="text" class="form-control search-input" name="searchName" value="${pageVO.cri.searchName }">
	                       <select class="form-control search-select" name="searchType">
	                            <option ${pageVO.cri.searchType eq 'title'?'selected':'' } value="title">제목</option>
	                            <option ${pageVO.cri.searchType eq 'content'?'selected':'' } value="content">내용</option>
	                            <option ${pageVO.cri.searchType eq 'writer'?'selected':'' } value="writer">작성자</option>
	                            <option ${pageVO.cri.searchType eq 'titcont'?'selected':'' } value="titcont">제목+내용</option>
	                       </select>
	                    </div>
	                    <input type="hidden" name="pageNum" value="1">
	                    <input type="hidden" name="amount" value="${pageVO.amount }">	                    
		    		</form>
                   
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th class="board-title">제목</th>
                                <th>작성자</th>
                                <th>등록일</th>
                                <th>수정일</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="vo" items="${list }">
                            <tr>
                                <td>${vo.bno }</td>
                                <td><a href="freeDetail?bno=${vo.bno }">${vo.title }</a></td>
                                <td>${vo.writer }</td>
                                <td><fmt:formatDate value="${vo.regdate }" pattern="yyyy년 MM월 dd일 hh:mm:ss"/></td>
                                <td><fmt:formatDate value="${vo.updatedate }" pattern="yyyy년 MM월 dd일 hh:mm:ss"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                        
                    </table>


                    <!--페이지 네이션을 가져옴-->
		    <%-- <form>
                    <div class="text-center">
                    <hr>
                    <ul class="pagination pagination-sm" >
                    <c:if test="${pageVO.prev }">
                        <li><a href="freeList?pageNum=${pageVO.startPage-1 }&amount=${pageVO.amount}">이전</a></li>
                    </c:if>
                    <!-- 1. 페이지네이션 번호 처리 -->
                    <c:forEach var="num" begin="${pageVO.startPage }" end="${pageVO.endPage}">
                        <li  class="${num == pageVO.pageNum?'active':''}"><a href="freeList?pageNum=${num }&amount=${pageVO.amount}">${num }</a></li>
                        
                    </c:forEach>
                    <c:if test="${pageVO.next }">    
                        <li><a href="freeList?pageNum=${pageVO.endPage+1 }&amount=${pageVO.amount}">다음</a></li>
                    </c:if>
                    </ul>
                    <button type="button" class="btn btn-info" onclick="location.href='freeRegist'">글쓰기</button>
                    </div>
		    </form> --%>
		    <form action="freeList" name="pageForm">
                    <div class="text-center">
                    <hr>
                    <ul class="pagination pagination-sm" >
                    <c:if test="${pageVO.prev }">
                        <li><a href="#" data-page="${pageVO.startPage-1}">이전</a></li>  <!-- a태그의 #의 의미 : 특정한 동작이 없을때 값,  -->
                    </c:if>
                    <!-- 1. 페이지네이션 번호 처리 -->
                    <c:forEach var="num" begin="${pageVO.startPage }" end="${pageVO.endPage}">
                        <li  class="${num == pageVO.pageNum?'active':''}"><a href="#" data-page="${num}">${num }</a></li>
                        
                    </c:forEach>
                    <c:if test="${pageVO.next }">    
                        <li><a href="#" data-page="${pageVO.endPage+1}">다음</a></li>
                    </c:if>
                    </ul>
                    	<c:if test="${sessionScope.userVO != null }">
                    		<button type="button" class="btn btn-info" onclick="location.href='freeRegist'">글쓰기</button>
                    	</c:if>
                    </div>
                    
                    <!-- 폼형식으로 보내는데 숨겨서 보낼값 hidden으로 표시 -->
                    <input type="hidden" name="pageNum" value="${pageVO.cri.pageNum }">
                    <input type="hidden" name="amount" value="${pageVO.cri.amount }">
                    <input type="hidden" name="searchType" value="${pageVO.cri.searchType }">
                    <input type="hidden" name="searchName" value="${pageVO.cri.searchName }">
		    </form>

                </div>
            </div>
        </div>
	</section>

	<script type="text/javascript">
		// 화면이 다 올라온 다음에 마지막에 실행해주라
		window.onload = function(){
			console.log(history.state);  // 현재브라우저 상태값
			console.dir(history);
			if(history.state === '') return;
			var msg = "${msg}";		// 컨트롤러에서 넘어온 메시지
			if(msg !== ''){
				alert(msg);
			}
			// history.replaceState(데이터, 페이지 제목, 변경할 주소) : 브라우저의 기록을 새롭게 변경해주는 기능
			// 이렇게 변경된 기록정보는 history.state 객체를 통해서 확인이 가능합니다.
			history.replaceState('',null,null);
		}
		
		
		// 페이지 네이션 관련
		/* 
			1. 페이지네이션을 a -> form태그로 변경
			2. Criteria클래스에 검색에 대한 키워드를 추가
			3. 검색폼과 페이지폼이 동일한 값을 가지고 hidden으로 넘어가도록 처리
			4. ql문을 동적쿼리로 변경
		*/
		
		// 이벤트 위임방식으로 페이지 네이션에 a태그에 이벤트를 전파
		var pagination = document.querySelector(".pagination");
		pagination.onclick=function(){
			//console.log(pagination); 

			// a태그의 고유이벤트를 막는다
			event.preventDefault();
			
			//console.log(event.target.tagName);
			if(event.target.tagName !== "A") return; // a태그가 아니라면 이 함수는 종료!
			
			//console.dir(event.target);
			var pageNum = event.target.dataset.page;
			//console.log(pageNum);
			
			//히든 폼에 pageNum타겟값을 저장
			document.pageForm.pageNum.value = pageNum
			
			// form의 값을 보내보자
			document.pageForm.submit();
		}
		
	</script>
