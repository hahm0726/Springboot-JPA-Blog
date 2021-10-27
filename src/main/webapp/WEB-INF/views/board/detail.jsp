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
      글번호: <span class="mr-2"><i>${board.id}</i></span>
      작성자: <span class="mr-2"><i>${board.user.username}</i></span>
      작성일자: <span class="mr-2"><i><fmt:formatDate value="${board.createDate}" pattern="yyyy-MM-dd HH:mm"/></i></span>
      조회수: <span><i>${board.count}</i></span>
    </div>
  </div>
  <hr/>
  <div>
    <div>${board.content}</div>
  </div>
  <hr/>

  <div class="card">
    <form>
      <input type="hidden" id="boardId" value="${board.id}">
      <div class="card-body">
        <textarea id="reply-content" class="form-control" rows="1"></textarea>
      </div>
      <div class="card-footer d-flex justify-content-end">
        <button type=button id="btn-reply-save" class="btn btn-primary">등록</button>
      </div>
    </form>
  </div>
  <div class="card">
    <div class="card-header">댓글 리스트</div>
    <ul id="reply--box" class="list-group">
      <c:forEach var="reply" items="${board.replies}">
        <li class="list-group-item d-flex justify-content-between">
          <div>${reply.content}</div>
          <div class="d-flex">
            <div class="font-italic">작성자: ${reply.user.username} &nbsp;</div>
            <c:if test="${principal.user.id==reply.user.id}">
              <button class="badge">삭제</button>
            </c:if>
          </div>
        </li>
      </c:forEach>
    </ul>
  </div>

</div>


<script src="/js/board.js"></script>
<%@include file="../layout/footer.jsp" %>




