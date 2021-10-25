<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../layout/header.jsp" %>

<div class="container">
  <div class="mb-3">
    <button class="btn btn-outline-secondary" onclick="history.back()">목록</button>
    <c:if test="${board.user.id == principal.user.id}">
      <a href="/board/${board.id}/updateForm" class="btn btn-outline-info">수정</a>
      <button id="btn-delete" class="btn btn-outline-danger">삭제</button>
    </c:if>
  </div>
  <div class="mb-3">
    <div>
      <h3>${board.title}</h3>
    </div>
    <div class="d-flex">
      글번호: <span id="boardId" class="mr-2"><i>${board.id}</i></span>
      작성자: <span id="boardUser" class="mr-2"><i>${board.user.username}</i></span>
      작성일자: <span id="boardDate" class="mr-2"><i><fmt:formatDate value="${board.createDate}" pattern="yyyy-MM-dd HH:mm"/></i></span>
      조회수: <span id="boardCnt"><i>${board.count}</i></span>
    </div>
  </div>
  <hr/>
  <div>
    <div>${board.content}</div>
  </div>
  <hr/>
</div>


<script src="/js/board.js"></script>
<%@include file="../layout/footer.jsp" %>




