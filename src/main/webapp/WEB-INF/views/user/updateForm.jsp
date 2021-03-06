<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../layout/header.jsp" %>

<div class="container">
  <form>
    <input id="userId" type="hidden" value="${principal.user.id}"/>
    <!--username 입력 시작-->
    <div class="form-group">
      <label for="username">Username:</label>
      <input type="text" class="form-control" placeholder="Username 입력" id="username" value="${principal.user.username}" readonly>
    </div>
    <!--username 입력 끝-->

    <!--password 입력 시작-->
    <div class="form-group">
      <label for="password">비밀번호:</label>
      <input type="password" class="form-control" placeholder="비밀번호 입력" id="password">
    </div>
    <!--password 입력 끝-->

    <!--email 입력 시작-->
    <div class="form-group">
      <label for="email">Email:</label>
      <input type="email" class="form-control" placeholder="Email 입력" id="email" value="${principal.user.email}">
    </div>
    <!--email 입력 끝-->

  </form>

  <!--가입 완료 버튼-->
  <button id="btn-update" class="btn btn-primary">수정 완료</button>
</div>

<script src="/js/user.js"></script>
<%@include file="../layout/footer.jsp" %>




