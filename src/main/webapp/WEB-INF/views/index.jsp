<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="layout/header.jsp" %>

<div class="container">
  <!--게시글 카드 목록 시작-->
  <c:forEach var="board" items="${boards.content}">
    <div class="card m-3">
      <div class="card-body">
        <h4 class="card-title">${board.title}</h4>
        <a href="/board/${board.id}" class="btn btn-primary stretched-link">상세보기</a>
      </div>
    </div>
  </c:forEach>
  <!--게시글 카드 목록 끝-->

  <ul class="pagination justify-content-center">
    <!--이전 페이지 버튼 시작-->
    <c:choose>
      <c:when test="${boards.first}">
        <li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
      </c:when>
      <c:otherwise>
        <li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
      </c:otherwise>
    </c:choose>
    <!--이전 페이지 버튼 끝-->
    <!--페이징 번호 목록 시작-->
    <c:forEach var="pageNum" begin="1" end="${boards.totalPages}">
      <li class="page-item"><a class="page-link" href="?page=${pageNum-1}">${pageNum}</a></li>
    </c:forEach>
    <!--페이징 번호 목록 끝-->
    <!--다음 페이지 버튼 시작 -->
    <c:choose>
      <c:when test="${boards.last}">
        <li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
      </c:when>
      <c:otherwise>
        <li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
      </c:otherwise>
    </c:choose>
    <!--다음 페이지 버튼 끝 -->
  </ul>
</div>

<%@include file="layout/footer.jsp" %>




