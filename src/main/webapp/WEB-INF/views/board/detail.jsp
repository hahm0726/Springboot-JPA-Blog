<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../layout/header.jsp" %>

<div class="container">
  <!--게시글 상단 버튼그룹 시작-->
  <div class="mb-3">
    <button class="btn btn-outline-secondary" onclick="history.back()">목록</button>
    <c:if test="${board.user.id == principal.user.id}">
      <a href="/board/${board.id}/updateForm" class="btn btn-outline-info">수정</a>
      <button id="btn-delete" class="btn btn-outline-danger">삭제</button>
    </c:if>
  </div>
  <!--게시글 상단 버튼그룹 끝-->
  <!--게시글 제목 및 상세정보(글번호, 작성자, 작성일자, 조회수) 시작-->
  <div class="mb-3">
    <!--게시글 제목 시작 -->
    <div>
      <h3>${board.title}</h3>
    </div>
    <!--게시글 제목 끝 -->
    <!--게시글 상세정보(글번호, 작성자, 작성일자, 조회수) 시작-->
    <div class="d-flex">
      글번호: <span class="mr-2"><i>${board.id}</i></span>
      작성자: <span class="mr-2"><i>${board.user.username}</i></span>
      작성일자: <span class="mr-2"><i><fmt:formatDate value="${board.createDate}" pattern="yyyy-MM-dd HH:mm"/></i></span>
      조회수: <span><i>${board.viewCount}</i></span>
    </div>
    <!--게시글 상세정보(글번호, 작성자, 작성일자, 조회수) 끝-->
  </div>
  <!--게시글 제목 및 상세정보(글번호, 작성자, 작성일자, 조회수) 끝-->
  <hr/>
  <!--게시글 내용 시작-->
  <div>
    <div>${board.content}</div>
    <div class="d-flex justify-content-center">
      <button id="btn-like" type="button" class="btn btn-lg border border-dark">
        <i class="far fa-heart heart"></i>
        <i class="fas fa-heart heart"></i>
      </button>
    </div>
  </div>
  <!--게시글 내용 끝-->
  <hr/>

  <!-- 댓글 작성 시작 -->
  <div class="card mb-2">
    <form>
      <input type="hidden" id="userId" value="${principal.user.id}"/>
      <input type="hidden" id="boardId" value="${board.id}"/>
      <div class="card-body">
        <textarea id="reply-content" class="form-control" rows="1"></textarea>
      </div>
      <div class="card-footer d-flex justify-content-end">
        <button type=button id="btn-reply-save" class="btn btn-primary badge">등록</button>
      </div>
    </form>
  </div>
  <!-- 댓글 작성 끝 -->
  <!-- 댓글 리스트 시작 -->
  <div class="card">
    <div class="card-header">댓글 리스트</div>
    <ul id="reply-box" class="list-group">
      <c:forEach var="reply" items="${board.replies}">
        <li id="reply-${reply.id}" class="list-group-item d-flex justify-content-between">
          <div>${reply.content}</div>
          <div class="d-flex">
            <div class="font-italic">작성자: ${reply.user.username} &nbsp;</div>
            <c:if test="${principal.user.id==reply.user.id}">
              <button onclick="index.replyDelete(${board.id},${reply.id})" class="badge">삭제</button>
            </c:if>
          </div>
        </li>
      </c:forEach>
    </ul>
  </div>
  <!-- 댓글 리스트 끝 -->

</div>


<script src="/js/board.js"></script>
<%@include file="../layout/footer.jsp" %>




